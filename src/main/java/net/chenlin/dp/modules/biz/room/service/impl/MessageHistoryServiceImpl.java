package net.chenlin.dp.modules.biz.room.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import net.chenlin.dp.modules.sys.dao.DomainsMapper;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.room.entity.MessageHistoryEntity;
import net.chenlin.dp.modules.biz.room.dao.MessageHistoryMapper;
import net.chenlin.dp.modules.biz.room.service.MessageHistoryService;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("messageHistoryService")
@AllArgsConstructor
public class MessageHistoryServiceImpl implements MessageHistoryService {

    private MessageHistoryMapper messageHistoryMapper;

	private DomainsMapper domainsMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	public Page<MessageHistoryEntity> listMessageHistory(Map<String, Object> params) {
		params.put("org_id",domainsMapper.getOrgIdByDomain(params.get("domain").toString()));
		Query query = new Query(params);
		Page<MessageHistoryEntity> page = new Page<>(query);
		List<MessageHistoryEntity> resp= messageHistoryMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param messageHistory
     * @return
     */
	public Resp<MessageHistoryEntity> saveMessageHistory(MessageHistoryEntity messageHistory) {
		int count = messageHistoryMapper.save(messageHistory);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	public Resp<MessageHistoryEntity> getMessageHistoryById(Long id) {
		MessageHistoryEntity messageHistory = messageHistoryMapper.getObjectById(id);
		return CommonUtils.msgResp(messageHistory);
	}

    /**
     * 修改
     * @param messageHistory
     * @return
     */
	public Resp<Integer> updateMessageHistory(MessageHistoryEntity messageHistory) {
		int count = messageHistoryMapper.update(messageHistory);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	public Resp batchRemove(String[] id) {
		int count = messageHistoryMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

	/**
	 * 删除群聊记录
	 * @param id
	 * @return
	 */
	public Resp batchRemoveRoomMsg(String[] id){
		int count = messageHistoryMapper.batchRemoveRoomMsg(id);
		return CommonUtils.msgResp(id, count);
	}

	public Long getPersonalMessageTotal(String domain) {
		return messageHistoryMapper.getPersonalMessageTotal(domainsMapper.getOrgIdByDomain(domain));
	}

	public Long getGroupMessageTotal(String domain) {
		return messageHistoryMapper.getGroupMessageTotal(domainsMapper.getOrgIdByDomain(domain));
	}
}
