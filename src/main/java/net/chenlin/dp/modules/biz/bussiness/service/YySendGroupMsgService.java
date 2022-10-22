package net.chenlin.dp.modules.biz.bussiness.service;

import java.util.List;
import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.YySendGroupMsgEntity;

/**
 * 运营-消息群发
 * @author wang<fangyuan.co@outlook.com>
 */
public interface YySendGroupMsgService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<YySendGroupMsgEntity> listYySendGroupMsg(Map<String, Object> params);

    /**
     * 新增
     * @param yySendGroupMsg
     * @return
     */
	Resp<YySendGroupMsgEntity> saveYySendGroupMsg(YySendGroupMsgEntity yySendGroupMsg);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<YySendGroupMsgEntity> getYySendGroupMsgById(Long id);

    /**
     * 修改
     * @param yySendGroupMsg
     * @return
     */
	Resp<Integer> updateYySendGroupMsg(YySendGroupMsgEntity yySendGroupMsg);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
