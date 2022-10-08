package net.chenlin.dp.modules.biz.room.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.room.entity.RoomMemberEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface RoomMemberService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<RoomMemberEntity> listRoomMember(Map<String, Object> params);

    /**
     * 新增
     * @param roomMember
     * @return
     */
	R saveRoomMember(RoomMemberEntity roomMember);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	R getRoomMemberById(Long id);

    /**
     * 修改
     * @param roomMember
     * @return
     */
	R updateRoomMember(RoomMemberEntity roomMember);

    /**
     * 删除
     * @param id
     * @return
     */
	R batchRemove(Long[] id);
	
}
