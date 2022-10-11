package net.chenlin.dp.modules.biz.room.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.chenlin.dp.common.entity.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.room.entity.RoomMemberEntity;
import net.chenlin.dp.modules.biz.room.service.RoomMemberService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/mr/roommember")
@Api(tags = "群组成员管理")
public class RoomMemberController extends AbstractController {
	
	@Autowired
	private RoomMemberService roomMemberService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	@ApiOperation(value = "群组成员列表")
	public Page<RoomMemberEntity> list(@RequestBody Map<String, Object> params) {
		return roomMemberService.listRoomMember(params);
	}
		
	/**
	 * 新增
	 * @param roomMember
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	@ApiOperation(value = "新增群组成员")
	public Resp save(@RequestBody RoomMemberEntity roomMember) {
		return roomMemberService.saveRoomMember(roomMember);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	@ApiOperation(value = "根据群id查询群组成员")
	public Resp getById(@RequestBody String id) {
		return roomMemberService.getRoomMemberById(id);
	}
	
	/**
	 * 修改
	 * @param roomMember
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public Resp update(@RequestBody RoomMemberEntity roomMember) {
		return roomMemberService.updateRoomMember(roomMember);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public Resp batchRemove(@RequestBody String[] id) {
		return roomMemberService.batchRemove(id);
	}
	
}