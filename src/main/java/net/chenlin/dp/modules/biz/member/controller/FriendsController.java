package net.chenlin.dp.modules.biz.member.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.member.entity.FriendsEntity;
import net.chenlin.dp.modules.biz.member.service.FriendsService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/mr/friends")
@Api(tags = "好友管理")
@DependsOn("springContextUtils")
@AllArgsConstructor
public class FriendsController extends AbstractController {

	private FriendsService friendsService;
	private MemberService memberService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "好友列表")
	public Page<MemberEntity> list(@RequestBody Map<String, Object> params) {
		return memberService.listFriends(params);
	}
		
	/**
	 * 新增
	 * @return
	 */
	@SysLog("新增")
	@PostMapping("/save")
	@ApiOperation(value = "新增好友")
	public Resp save(@RequestParam String mid,@RequestParam String friendId) {
		return friendsService.saveFriends(mid,friendId);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询好友")
	public Resp getById(@RequestParam String id) {
		return friendsService.getFriendsById(id);
	}
	
	/**
	 * 修改
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改好友备注")
	public Resp update(@RequestParam String mid,@RequestParam String friendId,@RequestParam String friendName) {
		return friendsService.updateFriendsNote(mid,friendId,friendName);
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@PostMapping("/remove")
	@ApiOperation(value = "删除好友")
	public Resp batchRemove(@RequestBody String[] id) {
		return friendsService.batchRemove(id);
	}

}
