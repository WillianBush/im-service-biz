package net.chenlin.dp.modules.biz.room.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.room.entity.RoomEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface RoomService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<RoomEntity> listRoom(Map<String, Object> params);

    /**
     * 新增
     * @param room
     * @return
     */
    Resp saveRoom(RoomEntity room);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Resp getRoomById(String id);

    /**
     * 修改
     * @param room
     * @return
     */
    Resp updateRoom(RoomEntity room);

    /**
     * 删除
     * @param id
     * @return
     */
    Resp batchRemove(String[] id);

    /**
     * 根据ID查询房间
     * @param id
     * @return
     */
    Resp getRoomMemberById(String id);
}
