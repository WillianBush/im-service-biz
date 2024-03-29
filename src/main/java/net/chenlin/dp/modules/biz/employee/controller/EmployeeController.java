package net.chenlin.dp.modules.biz.employee.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.exception.GoLoginException;
import net.chenlin.dp.modules.biz.bussiness.entity.YyIpListEntity;
import net.chenlin.dp.modules.biz.bussiness.service.YyIpListService;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.employee.entity.EmployeeEntity;
import net.chenlin.dp.modules.biz.employee.service.EmployeeService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@Api(tags = "特权用户")
@DependsOn("springContextUtils")
public class EmployeeController extends AbstractController {
	

	private EmployeeService employeeService;

	private YyIpListService yyIpListService;

	private MemberService memberService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<EmployeeEntity> list(@RequestBody Map<String, Object> params) {
		params.put("domain",getServerName());
		return employeeService.listEmployee(params);
	}
		
	/**
	 * 新增
	 * @param employee
	 * @return
	 */
	@SysLog("新增")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<EmployeeEntity> save(@RequestBody EmployeeEntity employee) {
		if (employee == null || ( StringUtils.isEmpty(employee.getMemberUuid()) && StringUtils.isEmpty(employee.getUsername()))){
			return Resp.error("参数错误");
		}
		MemberEntity member = null ;
		if (!StringUtils.isEmpty(employee.getUsername())){
			member = memberService.getByUserName(employee.getUsername());
		}

		if (!StringUtils.isEmpty(employee.getMemberUuid())){
			Resp<MemberEntity> memberResp = memberService.getMemberById(employee.getMemberUuid());
			member = memberResp.getData();
		}

		if (member == null){
			return Resp.error("用户不存在");
		}
		EmployeeEntity employeeEntity =employeeService.getMemberUUID(employee.getMemberUuid());
		if (employeeEntity != null){
			return Resp.error("用户已经是特权用户");
		}

		employeeService.saveEmployee(employee,member,getServerName());
		return Resp.ok("新增成功");
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<EmployeeEntity> getById(@RequestBody Long id) {
		return employeeService.getEmployeeById(id);
	}
	
	/**
	 * 修改
	 * @param employee
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody EmployeeEntity employee) {
		return employeeService.updateEmployee(employee);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody String[] id) {
		return employeeService.batchRemove(id);
	}


	/**
	 * 绑定ip白名单
	 * @param
	 * @return
	 */
	@SysLog("绑定ip白名单")
	@PostMapping("/bindIP")
	@ApiOperation(value = "绑定ip白名单")
	public Resp bindIP(@RequestBody EmployeeEntity employee) {
		if (employee.getIpWhite().isEmpty() || null == employee.getIpWhite()) {
			throw new GoLoginException("ip_white参数为空");
		}
		YyIpListEntity yyIpListEntity = yyIpListService.getByIP(employee.getIpWhite(), 0, getServerName());
		if (yyIpListEntity.getIpAddress().isEmpty()) {
			throw new GoLoginException("IP_white参数不存在");
		}
		return employeeService.employeeBindIp(employee,getServerName());
	}

	/**
	 * 特权用户在线
	 * @param id
	 * @return
	 */
	@SysLog("设置特权用户在线")
	@PostMapping("/enable")
	@ApiOperation(value = "设置特权用户在线")
	public Resp updateUserEnable(@RequestBody String[] id) {
		return employeeService.updateEmployeeEnable(id, 1);
	}

	/**
	 * 特权用户下线
	 * @param id
	 * @return
	 */
	@SysLog("设置特权用户下线")
	@PostMapping("/disable")
	@ApiOperation(value = "设置特权用户下线")
	public Resp updateUserDisable(@RequestBody String[] id) {
		return employeeService.updateEmployeeEnable(id, 0);
	}


}
