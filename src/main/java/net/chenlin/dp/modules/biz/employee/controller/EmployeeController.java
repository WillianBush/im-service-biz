package net.chenlin.dp.modules.biz.employee.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.employee.entity.EmployeeEntity;
import net.chenlin.dp.modules.biz.employee.service.EmployeeService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController extends AbstractController {
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<EmployeeEntity> list(@RequestBody Map<String, Object> params) {
		return employeeService.listEmployee(params);
	}
		
	/**
	 * 新增
	 * @param employee
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody EmployeeEntity employee) {
		return employeeService.saveEmployee(employee);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return employeeService.getEmployeeById(id);
	}
	
	/**
	 * 修改
	 * @param employee
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody EmployeeEntity employee) {
		return employeeService.updateEmployee(employee);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return employeeService.batchRemove(id);
	}
	
}
