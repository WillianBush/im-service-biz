package net.chenlin.dp.modules.biz.room.service.impl;

import java.util.Map;

import lombok.AllArgsConstructor;
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
	public R saveRoomMember(RoomMemberEntity roomMember) {
		int count = roomMemberMapper.save(roomMember);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getRoomMemberById(Long id) {
		RoomMemberEntity roomMember = roomMemberMapper.getObjectById(id);
		return CommonUtils.msg(roomMember);
	}

    /**
     * 修改
     * @param roomMember
     * @return
     */
	@Override
	public R updateRoomMember(RoomMemberEntity roomMember) {
		int count = roomMemberMapper.update(roomMember);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = roomMemberMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
