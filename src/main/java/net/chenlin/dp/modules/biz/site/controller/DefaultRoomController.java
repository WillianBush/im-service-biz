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
import net.chenlin.dp.modules.biz.site.entity.DefaultRoomEntity;
import net.chenlin.dp.modules.biz.site.service.DefaultRoomService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/site/defaultroom")
@Api(tags = "默认群组")
public class DefaultRoomController extends AbstractController {
	

	private DefaultRoomService defaultRoomService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<DefaultRoomEntity> list(@RequestBody Map<String, Object> params) {
		return defaultRoomService.listDefaultRoom(params);
	}
		
	/**
	 * 新增
	 * @param defaultRoom
	 * @return
	 */
	@SysLog("新增")
	@GetMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<DefaultRoomEntity> save(@RequestBody DefaultRoomEntity defaultRoom) {
		return defaultRoomService.saveDefaultRoom(defaultRoom);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<DefaultRoomEntity> getById(@RequestBody Long id) {
		return defaultRoomService.getDefaultRoomById(id);
	}
	
	/**
	 * 修改
	 * @param defaultRoom
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody DefaultRoomEntity defaultRoom) {
		return defaultRoomService.updateDefaultRoom(defaultRoom);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody Long[] id) {
		return defaultRoomService.batchRemove(id);
	}
	
}
