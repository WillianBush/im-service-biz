package net.chenlin.dp.modules.biz.room.dao;

import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.room.entity.RoomMemberEntity;
import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.room.entity.RoomEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface RoomMapper extends TkBaseMapper<RoomEntity> {

    RoomMemberEntity getRoomMemberById(String roomId);


    Long getRoomSum(Long orgId);

}
