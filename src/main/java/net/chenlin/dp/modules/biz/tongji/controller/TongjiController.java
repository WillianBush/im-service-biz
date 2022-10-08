package net.chenlin.dp.modules.biz.tongji.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.tongji.entity.TongjiEntity;
import net.chenlin.dp.modules.biz.tongji.service.TongjiService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tongji")
@Api(tags = "用户登录登出")
public class TongjiController extends AbstractController {
	

	private TongjiService tongjiService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	@ApiOperation(value = "列表")
	public Page<TongjiEntity> list(@RequestBody Map<String, Object> params) {
		return tongjiService.listTongji(params);
	}
		
	/**
	 * 新增
	 * @param tongji
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<TongjiEntity> save(@RequestBody TongjiEntity tongji) {
		return tongjiService.saveTongji(tongji);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<TongjiEntity> getById(@RequestBody Long id) {
		return tongjiService.getTongjiById(id);
	}
	
	/**
	 * 修改
	 * @param tongji
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody TongjiEntity tongji) {
		return tongjiService.updateTongji(tongji);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody Long[] id) {
		return tongjiService.batchRemove(id);
	}
	
}
