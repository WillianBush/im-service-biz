package net.chenlin.dp.modules.biz.member.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class FriendsController extends AbstractController {
	
	@Autowired
	private FriendsService friendsService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	@ApiOperation(value = "用户列表")
	public Page<FriendsEntity> list(@RequestBody Map<String, Object> params) {
		return friendsService.listFriends(params);
	}
		
	/**
	 * 新增
	 * @param friends
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	@ApiOperation(value = "新增用户")
	public R save(@RequestBody FriendsEntity friends) {
		return friendsService.saveFriends(friends);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	@ApiOperation(value = "根据id查询好友")
	public R getById(@RequestBody Long id) {
		return friendsService.getFriendsById(id);
	}
	
	/**
	 * 修改
	 * @param friends
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	@ApiOperation(value = "修改好友备注")
	public R update(@RequestBody FriendsEntity friends) {
		return friendsService.updateFriends(friends);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	@ApiOperation(value = "删除好友")
	public R batchRemove(@RequestBody Long[] id) {
		return friendsService.batchRemove(id);
	}
	
}
