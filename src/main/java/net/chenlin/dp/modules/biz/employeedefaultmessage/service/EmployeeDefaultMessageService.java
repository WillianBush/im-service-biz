package net.chenlin.dp.modules.biz.employeedefaultmessage.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.employeedefaultmessage.entity.EmployeeDefaultMessageEntity;

/**
 * 员工默认消息
 * @author wang<fangyuan.co@outlook.com>
 */
public interface EmployeeDefaultMessageService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<EmployeeDefaultMessageEntity> listEmployeeDefaultMessage(Map<String, Object> params);

    /**
     * 新增
     * @param employeeDefaultMessage
     * @return
     */
	Resp<EmployeeDefaultMessageEntity> saveEmployeeDefaultMessage(EmployeeDefaultMessageEntity employeeDefaultMessage);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<EmployeeDefaultMessageEntity> getEmployeeDefaultMessageById(Long id);

    /**
     * 修改
     * @param employeeDefaultMessage
     * @return
     */
	Resp<Integer> updateEmployeeDefaultMessage(EmployeeDefaultMessageEntity employeeDefaultMessage);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Object[] id);
	
}
