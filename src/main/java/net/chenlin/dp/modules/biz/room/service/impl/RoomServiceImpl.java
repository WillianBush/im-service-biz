package net.chenlin.dp.modules.biz.room.service.impl;

import java.util.Map;

import net.chenlin.dp.modules.biz.room.entity.RoomMemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.room.entity.RoomEntity;
import net.chenlin.dp.modules.biz.room.dao.RoomMapper;
import net.chenlin.dp.modules.biz.room.service.RoomService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("roomService")
public class RoomServiceImpl implements RoomService {

	@Autowired
    private RoomMapper roomMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<RoomEntity> listRoom(Map<String, Object> params) {
		Query query = new Query(params);
		Page<RoomEntity> page = new Page<>(query);
		page.setRows(roomMapper.listForPage(page, query));
		return page;
	}

    /**
     * 新增
     * @param room
     * @return
     */
	@Override
	public R saveRoom(RoomEntity room) {
		int count = roomMapper.save(room);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getRoomById(Long id) {
		RoomEntity room = roomMapper.getObjectById(id);
		return CommonUtils.msg(room);
	}

    /**
     * 修改
     * @param room
     * @return
     */
	@Override
	public R updateRoom(RoomEntity room) {
		int count = roomMapper.update(room);
		return CommonUtils.msg(count);
	}

    /**
	 * 删除
	 * @param id
	 * @return
	 */
	@Override
	public R batchRemove(Long[] id) {
		int count = roomMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@Override
	public R getRoomMemberById(String id) {
		RoomMemberEntity room = roomMapper.getRoomMemberById(id);
		return CommonUtils.msg(room);
	}
}
