package net.chenlin.dp.modules.biz.employeedefaultmessage.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.employeedefaultmessage.entity.EmployeeDefaultMessageEntity;
import net.chenlin.dp.modules.biz.employeedefaultmessage.dao.EmployeeDefaultMessageMapper;
import net.chenlin.dp.modules.biz.employeedefaultmessage.service.EmployeeDefaultMessageService;


/**
 * 员工默认消息
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("employeeDefaultMessageService")
@AllArgsConstructor
public class EmployeeDefaultMessageServiceImpl implements EmployeeDefaultMessageService {

    private EmployeeDefaultMessageMapper employeeDefaultMessageMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<EmployeeDefaultMessageEntity> listEmployeeDefaultMessage(Map<String, Object> params) {
		Query query = new Query(params);
		Page<EmployeeDefaultMessageEntity> page = new Page<>(query);
		List<EmployeeDefaultMessageEntity> resp= employeeDefaultMessageMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param employeeDefaultMessage
     * @return
     */
	@Override
	public Resp<EmployeeDefaultMessageEntity> saveEmployeeDefaultMessage(EmployeeDefaultMessageEntity employeeDefaultMessage) {
		int count = employeeDefaultMessageMapper.save(employeeDefaultMessage);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<EmployeeDefaultMessageEntity> getEmployeeDefaultMessageById(Long id) {
		EmployeeDefaultMessageEntity employeeDefaultMessage = employeeDefaultMessageMapper.getObjectById(id);
		return CommonUtils.msgResp(employeeDefaultMessage);
	}

    /**
     * 修改
     * @param employeeDefaultMessage
     * @return
     */
	@Override
	public Resp<Integer> updateEmployeeDefaultMessage(EmployeeDefaultMessageEntity employeeDefaultMessage) {
		int count = employeeDefaultMessageMapper.update(employeeDefaultMessage);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = employeeDefaultMessageMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
