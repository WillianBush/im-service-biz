package net.chenlin.dp.modules.biz.room.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.room.entity.RoomEntity;
import net.chenlin.dp.modules.biz.room.service.RoomService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/mr/room")
public class RoomController extends AbstractController {
	
	@Autowired
	private RoomService roomService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<RoomEntity> list(@RequestBody Map<String, Object> params) {
		return roomService.listRoom(params);
	}
		
	/**
	 * 新增
	 * @param room
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody RoomEntity room) {
		return roomService.saveRoom(room);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return roomService.getRoomById(id);
	}
	
	/**
	 * 修改
	 * @param room
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody RoomEntity room) {
		return roomService.updateRoom(room);
	}
	
	/**
	 * 删除 解散
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return roomService.batchRemove(id);
	}

	/**
	 * 添加成员
	 * @param room
	 * @return
	 */
	@SysLog("添加成员")
	@RequestMapping("/addMember")
	public R addMember(@RequestBody RoomEntity room) {
		return roomService.updateRoom(room);
	}


}
