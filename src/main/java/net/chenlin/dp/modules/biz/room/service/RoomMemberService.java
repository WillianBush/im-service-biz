package net.chenlin.dp.modules.biz.room.service;

import java.util.List;
import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
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
	Page<MemberEntity> listRoomMember(Map<String, Object> params);

    /**
     * 新增
     * @param roomMember
     * @return
     */
    Resp saveRoomMember(RoomMemberEntity roomMember);


    /**
     * 修改
     * @param roomMember
     * @return
     */
    Resp updateRoomMember(RoomMemberEntity roomMember);

    /**
     * 删除
     * @param id
     * @return
     */
    Resp batchRemove(String[] id);

    /**
     * 新增
     * @param roomMember
     * @return
     */
    Resp batchSaveRoomMember(List<RoomMemberEntity> roomMember);
	
}
