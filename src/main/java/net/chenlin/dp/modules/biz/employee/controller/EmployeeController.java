package net.chenlin.dp.modules.biz.employee.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Resp batchRemove(@RequestBody Long[] id) {
		return employeeService.batchRemove(id);
	}
	
}
