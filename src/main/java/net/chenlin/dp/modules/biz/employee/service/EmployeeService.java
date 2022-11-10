package net.chenlin.dp.modules.biz.employee.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.employee.entity.EmployeeEntity;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;

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
	Resp<Integer> saveEmployee(EmployeeEntity employee);


    Integer saveEmployee(EmployeeEntity employee,MemberEntity member);
    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<EmployeeEntity> getEmployeeById(Long id);

    EmployeeEntity getMemberUUID(String memberUUID);

    EmployeeEntity getByMemberId(String memberId);


    /*
     * 修改
     * @param employee
     * @return
     */
	Resp<Integer> updateEmployee(EmployeeEntity employee);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(String[] id);

    Resp<Integer> employeeBindIp(EmployeeEntity employee);

	
}
