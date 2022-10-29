package net.chenlin.dp.modules.biz.room.service.impl;

import java.util.*;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.*;
import net.chenlin.dp.modules.biz.member.dao.MemberMapper;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.room.dao.RoomMemberMapper;
import net.chenlin.dp.modules.biz.room.entity.RoomMemberEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.room.entity.RoomEntity;
import net.chenlin.dp.modules.biz.room.dao.RoomMapper;
import net.chenlin.dp.modules.biz.room.service.RoomService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("roomService")
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomMapper roomMapper;


	private OSSModel ossModel;

	private RoomMemberMapper roomMemberMapper;


	private MemberMapper memberMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<RoomEntity> listRoom(Map<String, Object> params) {
		Query query = new Query(params);
		Page<RoomEntity> page = new Page<>(query);
		List<RoomEntity> roomEntityList = roomMapper.listForPage(page, query);
		for (RoomEntity room : roomEntityList) {
			getRoomHeadImg(room);
			getMembersByRoom(room);
		}
		page.setRows(roomEntityList);
		return page;
	}

    /**
     * 新增
     * @param room
     * @return
     */
	@Override
	public Resp saveRoom(RoomEntity room) {
		int count = roomMapper.save(room);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp getRoomById(String id) {
		RoomEntity room = roomMapper.getObjectById(id);
		getMembersByRoom(room);
		return CommonUtils.msgResp(room);
	}

    /**
     * 修改
     * @param room
     * @return
     */
	@Override
	public Resp updateRoom(RoomEntity room) {
		int count = roomMapper.update(room);
		return CommonUtils.msgResp(count);
	}

    /**
	 * 删除
	 * @param id
	 * @return
	 */
	@Override
	public Resp batchRemove(String[] id) {
		int count = roomMapper.batchRemove(id);
		/**删除中间表数据*/
		if(count>0){
			roomMemberMapper.batchRemoveByRoomId(id);
		}
		return CommonUtils.msgResp(id, count);
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@Override
	public Resp getRoomMemberById(String id) {
		RoomMemberEntity room = roomMapper.getRoomMemberById(id);
		return CommonUtils.msgResp(room);
	}

	
	@Override
	public Long getTotal() {
		return roomMapper.getRoomSum();
	}

	private void getRoomHeadImg(RoomEntity room){
		if (StringUtils.isEmpty(room.getHeadimg())){
			room.setHeadimg("https://"+ossModel.getEndpoint() + "/img_sys/defaultHeadPic.jpg");
		}else {
			room.setHeadimg("https://"+ossModel.getEndpoint() +room.getHeadimg());
		}
	}

	/**
	 * 获取群组成员
	 * @return
	 */
	private RoomEntity getMembersByRoom(RoomEntity room){
		List<RoomMemberEntity> roomMemberEntities =roomMemberMapper.select(RoomMemberEntity.builder()
				.room_id(room.getId())
				.build());
		if (!roomMemberEntities.isEmpty()) {
			List<MemberEntity> memberEntities = memberMapper.getByIds(roomMemberEntities.stream().map(RoomMemberEntity::getMember_id).toArray());
			room.setMembers(memberEntities);
		}else {
			room.setMembers(Collections.emptyList());
		}
		return room;
	}
}
