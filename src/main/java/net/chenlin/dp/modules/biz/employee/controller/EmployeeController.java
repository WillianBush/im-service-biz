package net.chenlin.dp.modules.biz.employee.controller;

import java.util.Date;
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
		if (employee == null || StringUtils.isEmpty(employee.getMember_uuid())){
			return Resp.error("参数错误");
		}
		Resp<MemberEntity> memberResp = memberService.getMemberById(employee.getMember_uuid());
		MemberEntity member= memberResp.getData();
		if (member == null){
			return Resp.error("用户不存在");
		}
		EmployeeEntity employeeEntity =employeeService.getMemberUUID(employee.getMember_uuid());
		if (employeeEntity != null){
			return Resp.error("用户已经是特权用户");
		}
		employee.setMember_id(member.getMemberid());
		employee.setLastLoginIp(member.getLastloginip());
		employee.setCreateDate(new Date());
		employee.setName(member.getNickname());
		return employeeService.saveEmployee(employee);
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
		if (employee.getIp_white().isEmpty() || null == employee.getIp_white()) {
			throw new GoLoginException("ip_white参数为空");
		}
		YyIpListEntity yyIpListEntity = yyIpListService.getByIP(employee.getIp_white(), 0);
		if (yyIpListEntity.getIp_address().isEmpty()) {
			throw new GoLoginException("IP_white参数不存在");
		}
		return employeeService.employeeBindIp(employee);
	}
}
