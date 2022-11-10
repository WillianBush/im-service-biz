package net.chenlin.dp.modules.biz.employee.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.utils.IdGenerate;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.employee.entity.EmployeeEntity;
import net.chenlin.dp.modules.biz.employee.dao.EmployeeMapper;
import net.chenlin.dp.modules.biz.employee.service.EmployeeService;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("employeeService")
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeMapper employeeMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<EmployeeEntity> listEmployee(Map<String, Object> params) {
		params.put("org_id", 1);
		Query query = new Query(params);
		Page<EmployeeEntity> page = new Page<>(query);
		List<EmployeeEntity> resp= employeeMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param employee
     * @return
     */
	@Override
	public Resp<Integer> saveEmployee(EmployeeEntity employee) {
		employee.setOrg_id(1);
		employee.setId(IdGenerate.generateUUID());
		int count = employeeMapper.save(employee);
		return CommonUtils.msgResp(count);
	}

	@Override
	public Integer saveEmployee(EmployeeEntity employee,MemberEntity member) {
		employee.setMember_id(member.getMemberid());
		employee.setLastLoginIp(member.getLastloginip());
		employee.setCreateDate(new Date());
		employee.setName(member.getNickname());
		employee.setMember_uuid(member.getId());
		return this.saveEmployee(employee).getData();
	}

	/**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<EmployeeEntity> getEmployeeById(Long id) {
		//Todo 需要处理baseMaper中方法
		EmployeeEntity employee = employeeMapper.getObjectById(id);
		return CommonUtils.msgResp(employee);
	}

	@Override
	public EmployeeEntity getMemberUUID(String memberUUID) {
		return employeeMapper.getMemberUUID(memberUUID, 1);
	}


	@Override
	public EmployeeEntity getByMemberId(String memberId) {
		return employeeMapper.getByMemberId(memberId, 1);
	}

	/**
     * 修改
     * @param employee
     * @return
     */
	@Override
	public Resp<Integer> updateEmployee(EmployeeEntity employee) {
		employee.setOrg_id(1);
		int count = employeeMapper.update(employee);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(String[] id) {
		int count = employeeMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

	@Override
	public Resp<Integer> employeeBindIp(EmployeeEntity employee) {
		if (employee.getMember_id().isEmpty() || null == employee.getMember_id()) {
			return Resp.error("用户ID未空");
		}
		employee.setOrg_id(1);
		int count = employeeMapper.bindIP(employee);
		return CommonUtils.msgResp(count);
	}

}
