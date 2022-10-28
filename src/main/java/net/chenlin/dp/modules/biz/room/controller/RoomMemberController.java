package net.chenlin.dp.modules.biz.room.controller;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

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
@DependsOn("springContextUtils")
public class RoomMemberController extends AbstractController {
	
	@Autowired
	private RoomMemberService roomMemberService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@GetMapping("/list")
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
	@PostMapping("/save")
	@ApiOperation(value = "新增群组成员")
	public Resp save(@RequestBody RoomMemberEntity roomMember) {
		return roomMemberService.saveRoomMember(roomMember);
	}


	/**
	 * 批量添加成员
	 * @param room
	 * @return
	 */
	@SysLog("批量添加成员")
	@PostMapping("/batchAddMember")
	@ApiOperation(value = "批量添加成员")
	public Resp batchAddMember(@RequestBody List<RoomMemberEntity> room) {
		return roomMemberService.batchSaveRoomMember(room);
	}

	
	/**
	 * 修改
	 * @param roomMember
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp update(@RequestBody RoomMemberEntity roomMember) {
		return roomMemberService.updateRoomMember(roomMember);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody String[] id) {
		return roomMemberService.batchRemove(id);
	}

	/**
	 * 设为管理员
	 * @param room
	 * @return
	 */
	@SysLog("设为管理员")
	@PostMapping("/addRoomMgr")
	@ApiOperation(value = "设为管理员")
	public Resp addRoomMgr(@RequestBody RoomMemberEntity room) {
		/**状态1位管理员*/
		room.setIs_manager(1);
		return roomMemberService.updateRoomMember(room);
	}


	/**
	 * 是否禁言
	 * @param room
	 * @return
	 */
	@SysLog("设置禁言")
	@PostMapping("/setStopSpeaker")
	@ApiOperation(value = "设置禁言")
	public Resp setStopSpeaker(@RequestBody RoomMemberEntity room) {
		/**状态1位管理员*/
		room.setStop_speaker(1);
		return roomMemberService.updateRoomMember(room);
	}
}
