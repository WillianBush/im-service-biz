package net.chenlin.dp.modules.biz.employeedefaultmessage.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.entity.OSSModel;
import net.chenlin.dp.common.utils.IdGenerate;
import net.chenlin.dp.modules.sys.dao.DomainsMapper;
import org.apache.commons.lang.StringUtils;
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
@Slf4j
public class EmployeeDefaultMessageServiceImpl implements EmployeeDefaultMessageService {

    private EmployeeDefaultMessageMapper employeeDefaultMessageMapper;
	private OSSModel ossModel;

	private DomainsMapper domainsMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<EmployeeDefaultMessageEntity> listEmployeeDefaultMessage(Map<String, Object> params) {
		params.put("org_id",domainsMapper.getOrgIdByDomain(params.get("domain").toString()));
		Query query = new Query(params);
		Page<EmployeeDefaultMessageEntity> page = new Page<>(query);
		List<EmployeeDefaultMessageEntity> resp= employeeDefaultMessageMapper.listForPage(page, query);
		for (EmployeeDefaultMessageEntity employeeDefaultMessage: resp) {
			if (!StringUtils.isEmpty(employeeDefaultMessage. getPicture1())){
				employeeDefaultMessage.setPicture1("https://"+ossModel.getEndpoint() +employeeDefaultMessage.getPicture1());
			}
			if (!StringUtils.isEmpty(employeeDefaultMessage.getPicture2())){
				employeeDefaultMessage.setPicture2("https://"+ossModel.getEndpoint() +employeeDefaultMessage.getPicture2());
			}
			if (!StringUtils.isEmpty(employeeDefaultMessage.getPicture3())){
				employeeDefaultMessage.setPicture3("https://"+ossModel.getEndpoint() +employeeDefaultMessage.getPicture3());
			}
			if (!StringUtils.isEmpty(employeeDefaultMessage.getPicture4())){
				employeeDefaultMessage.setPicture4("https://"+ossModel.getEndpoint() +employeeDefaultMessage.getPicture4());
			}
			if (!StringUtils.isEmpty(employeeDefaultMessage.getPicture5())){
				employeeDefaultMessage.setPicture5("https://"+ossModel.getEndpoint() +employeeDefaultMessage.getPicture5());
			}
		}
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param employeeDefaultMessage
     * @return
     */
	@Override
	public Resp<EmployeeDefaultMessageEntity> saveEmployeeDefaultMessage(EmployeeDefaultMessageEntity employeeDefaultMessage,String domain) {
		employeeDefaultMessage.setId(IdGenerate.generateUUID());
		employeeDefaultMessage.setOrgId(domainsMapper.getOrgIdByDomain(domain));
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
	public Resp batchRemove(Object[] id) {
		int count = employeeDefaultMessageMapper.batchRemove(id);
		log.info("batchRemove-id.length:{}", id.length);
		log.info("batchRemove-count:{}", count);
		return CommonUtils.msgResp(id, count);
	}

	@Override
	public EmployeeDefaultMessageEntity getEmployeeDefaultMessageByInviteCode(String inviteCode) {
		return	employeeDefaultMessageMapper.selectOne(EmployeeDefaultMessageEntity.builder()
						.inviteCode(inviteCode)
				.build());
	}

}
