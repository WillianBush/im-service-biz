package net.chenlin.dp.modules.biz.room.dao;

import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.YyGroupMsgDayEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.YyPersonalMsgDayEntity;
import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.room.entity.MessageHistoryEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

import java.util.List;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface MessageHistoryMapper extends TkBaseMapper<MessageHistoryEntity> {

    int batchRemoveRoomMsg(String[] id);

    List<YyPersonalMsgDayEntity> getObjectPersonalMessageGroupByDate(Query query);

    List<YyGroupMsgDayEntity> getObjectGroupMessageGroupByDate(Query query);

    int deleteByFromUid(String uid);

    Long getGroupMessageTotal();

    Long getPersonalMessageTotal();
}
