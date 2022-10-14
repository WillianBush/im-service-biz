package net.chenlin.dp.modules.biz.room.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.room.entity.MessageHistoryEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface MessageHistoryMapper extends TkBaseMapper<MessageHistoryEntity> {

    public int batchRemoveRoomMsg(String[] id);
}
