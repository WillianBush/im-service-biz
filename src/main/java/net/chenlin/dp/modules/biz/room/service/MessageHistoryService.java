package net.chenlin.dp.modules.biz.room.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.room.entity.MessageHistoryEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface MessageHistoryService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<MessageHistoryEntity> listMessageHistory(Map<String, Object> params);

    /**
     * 新增
     * @param messageHistory
     * @return
     */
	Resp<MessageHistoryEntity> saveMessageHistory(MessageHistoryEntity messageHistory);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<MessageHistoryEntity> getMessageHistoryById(Long id);

    /**
     * 修改
     * @param messageHistory
     * @return
     */
	Resp<Integer> updateMessageHistory(MessageHistoryEntity messageHistory);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
