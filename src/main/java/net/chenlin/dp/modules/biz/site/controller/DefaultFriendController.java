package net.chenlin.dp.modules.biz.site.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.site.entity.DefaultFriendEntity;
import net.chenlin.dp.modules.biz.site.service.DefaultFriendService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/site/defaultfriend")
@Api(tags = "默认好友")
public class DefaultFriendController extends AbstractController {
	

	private DefaultFriendService defaultFriendService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<DefaultFriendEntity> list(@RequestBody Map<String, Object> params) {
		return defaultFriendService.listDefaultFriend(params);
	}
		
	/**
	 * 新增
	 * @param defaultFriend
	 * @return
	 */
	@SysLog("新增")
	@GetMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<DefaultFriendEntity> save(@RequestBody DefaultFriendEntity defaultFriend) {
		return defaultFriendService.saveDefaultFriend(defaultFriend);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<DefaultFriendEntity> getById(@RequestBody String id) {
		return defaultFriendService.getDefaultFriendById(id);
	}
	
	/**
	 * 修改
	 * @param defaultFriend
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody DefaultFriendEntity defaultFriend) {
		return defaultFriendService.updateDefaultFriend(defaultFriend);
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
		return defaultFriendService.batchRemove(id);
	}
	
}
