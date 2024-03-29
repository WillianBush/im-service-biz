package net.chenlin.dp.modules.biz.room.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.room.entity.RoomMemberEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

import java.util.List;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface RoomMemberMapper extends TkBaseMapper<RoomMemberEntity> {

    int batchRemoveByRoomId(Object[] id);

    int batchAddRobot(List<RoomMemberEntity> robotList);

    String getRoomMemberIds(String roomId);

    RoomMemberEntity getRoomMember(RoomMemberEntity roomMemberEntity);
}
