package net.chenlin.dp.modules.biz.room.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.SnowFlakeIdWorker;
import net.chenlin.dp.modules.biz.member.dao.MemberMapper;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.room.entity.RoomMemberEntity;
import net.chenlin.dp.modules.biz.room.dao.RoomMemberMapper;
import net.chenlin.dp.modules.biz.room.service.RoomMemberService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("roomMemberService")
@AllArgsConstructor
public class RoomMemberServiceImpl implements RoomMemberService {

    private RoomMemberMapper roomMemberMapper;
    private MemberMapper memberMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<RoomMemberEntity> listRoomMember(Map<String, Object> params) {
		Query query = new Query(params);
		Page<RoomMemberEntity> page = new Page<>(query);
		page.setRows(roomMemberMapper.listForPage(page, query));
		return page;
	}

    /**
     * 新增
     * @param roomMember
     * @return
     */
	@Override
	public Resp saveRoomMember(RoomMemberEntity roomMember) {
		SnowFlakeIdWorker sw=new SnowFlakeIdWorker(1);
		roomMember.setId(sw.createId());
		roomMember.setCreateDate(new Date());
		/*** 根据memberId 查询 member 得到 id */
		MemberEntity memberEntity=memberMapper.getMemberByMid(roomMember.getMember_id());
		roomMember.setMember_id(memberEntity.getId());
		int count = roomMemberMapper.save(roomMember);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp getRoomMemberById(String id) {
		RoomMemberEntity roomMember = roomMemberMapper.getObjectById(id);
		return CommonUtils.msgResp(roomMember);
	}

    /**
     * 修改
     * @param roomMember
     * @return
     */
	@Override
	public Resp updateRoomMember(RoomMemberEntity roomMember) {
		roomMember.setModifyDate(new Date());
		int count = roomMemberMapper.update(roomMember);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(String[] id) {
		int count = roomMemberMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

	@Override
	public Resp batchSaveRoomMember(List<RoomMemberEntity> roomMembers) {
		roomMembers.forEach(rm->{
			saveRoomMember(rm);
		});
		return Resp.ok();
	}

}
