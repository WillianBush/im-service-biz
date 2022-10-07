package net.chenlin.dp.modules.biz.employee.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.employee.entity.EmployeeEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface EmployeeService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<EmployeeEntity> listEmployee(Map<String, Object> params);

    /**
     * 新增
     * @param employee
     * @return
     */
	R saveEmployee(EmployeeEntity employee);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	R getEmployeeById(Long id);

    /**
     * 修改
     * @param employee
     * @return
     */
	R updateEmployee(EmployeeEntity employee);

    /**
     * 删除
     * @param id
     * @return
     */
	R batchRemove(Long[] id);
	
}
