package net.chenlin.dp.modules.biz.room.controller;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.chenlin.dp.common.entity.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "群组管理")
@DependsOn("springContextUtils")
public class RoomController extends AbstractController {
	
	@Autowired
	private RoomService roomService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "群组列表")
	public Page<RoomEntity> list(@RequestBody Map<String, Object> params) {
		return roomService.listRoom(params);
	}
		
	/**
	 * 新增
	 * @param room
	 * @return
	 */
	@SysLog("新增")
	@PostMapping("/save")
	@ApiOperation(value = "新增群组")
	public Resp save(@RequestBody RoomEntity room) {
		return roomService.saveRoom(room);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "群组详情")
	public Resp getById(@RequestParam String id) {
		return roomService.getRoomById(id);
	}
	
	/**
	 * 修改
	 * @param room
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改群组")
	public Resp update(@RequestBody RoomEntity room) {
		return roomService.updateRoom(room);
	}
	
	/**
	 * 删除 解散
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@PostMapping("/remove")
	@ApiOperation(value = "删除解散群组")
	public Resp batchRemove(@RequestBody String[] id) {
		return roomService.batchRemove(id);
	}
}
