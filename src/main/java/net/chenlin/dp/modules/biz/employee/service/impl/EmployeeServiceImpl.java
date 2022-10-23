package net.chenlin.dp.modules.biz.employee.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
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
	public Resp<EmployeeEntity> saveEmployee(EmployeeEntity employee) {
		int count = employeeMapper.save(employee);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<EmployeeEntity> getEmployeeById(Long id) {
		EmployeeEntity employee = employeeMapper.getObjectById(id);
		return CommonUtils.msgResp(employee);
	}

	@Override
	public EmployeeEntity getMemberUUID(String memberUUID) {
		return employeeMapper.selectOne(EmployeeEntity.builder()
						.member_uuid(memberUUID)
				.build());
	}

	/**
     * 修改
     * @param employee
     * @return
     */
	@Override
	public Resp<Integer> updateEmployee(EmployeeEntity employee) {
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

		int count = employeeMapper.bindIP(employee);
		return CommonUtils.msgResp(count);
	}

}
