package net.chenlin.dp.modules.biz.employeedefaultmessage.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.modules.biz.employee.entity.EmployeeEntity;
import net.chenlin.dp.modules.biz.employee.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.context.annotation.DependsOn;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.employeedefaultmessage.entity.EmployeeDefaultMessageEntity;
import net.chenlin.dp.modules.biz.employeedefaultmessage.service.EmployeeDefaultMessageService;

/**
 * 员工默认消息
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/employeedefaultmessage")
@Api(tags = "员工默认消息")
@DependsOn("springContextUtils")
public class EmployeeDefaultMessageController extends AbstractController {
	

	private EmployeeDefaultMessageService employeeDefaultMessageService;

	private EmployeeService employeeService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<EmployeeDefaultMessageEntity> list(@RequestBody Map<String, Object> params) {
		return employeeDefaultMessageService.listEmployeeDefaultMessage(params);
	}
		
	/**
	 * 新增
	 * @param employeeDefaultMessage
	 * @return
	 */
	@SysLog("新增员工默认消息")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<EmployeeDefaultMessageEntity> save(@RequestBody EmployeeDefaultMessageEntity employeeDefaultMessage) {
		if (employeeDefaultMessage == null || employeeDefaultMessage.getEmployee_id() == null || StringUtils.isEmpty(employeeDefaultMessage.getInvite_code())|| StringUtils.isEmpty(employeeDefaultMessage.getMember_id())){
			return Resp.error("参数错误");
		}
		EmployeeEntity employee = employeeService.getByMemberId(employeeDefaultMessage.getMember_id());
		if (employee == null){
			return Resp.error("参数错误");
		}
		employeeDefaultMessage.setEmployee_id(employee.getId());
		return employeeDefaultMessageService.saveEmployeeDefaultMessage(employeeDefaultMessage);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<EmployeeDefaultMessageEntity> getById(@RequestBody Long id) {
		return employeeDefaultMessageService.getEmployeeDefaultMessageById(id);
	}
	
	/**
	 * 修改
	 * @param employeeDefaultMessage
	 * @return
	 */
	@SysLog("修改员工默认消息")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody EmployeeDefaultMessageEntity employeeDefaultMessage) {
		return employeeDefaultMessageService.updateEmployeeDefaultMessage(employeeDefaultMessage);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除员工默认消息")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody Object[] id) {
		return employeeDefaultMessageService.batchRemove(id);
	}
	
}
