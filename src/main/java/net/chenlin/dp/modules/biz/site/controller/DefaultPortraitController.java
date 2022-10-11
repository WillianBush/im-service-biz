package net.chenlin.dp.modules.biz.site.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.site.entity.DefaultPortraitEntity;
import net.chenlin.dp.modules.biz.site.service.DefaultPortraitService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("//site/defaultportrait")
@Api(tags = "默认头像")
@DependsOn("springContextUtils")
public class DefaultPortraitController extends AbstractController {
	

	private DefaultPortraitService defaultPortraitService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<DefaultPortraitEntity> list(@RequestBody Map<String, Object> params) {
		return defaultPortraitService.listDefaultPortrait(params);
	}
		
	/**
	 * 新增
	 * @param defaultPortrait
	 * @return
	 */
	@SysLog("新增")
	@GetMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<DefaultPortraitEntity> save(@RequestBody DefaultPortraitEntity defaultPortrait) {
		return defaultPortraitService.saveDefaultPortrait(defaultPortrait);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<DefaultPortraitEntity> getById(@RequestBody String id) {
		return defaultPortraitService.getDefaultPortraitById(id);
	}
	
	/**
	 * 修改
	 * @param defaultPortrait
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody DefaultPortraitEntity defaultPortrait) {
		return defaultPortraitService.updateDefaultPortrait(defaultPortrait);
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
		return defaultPortraitService.batchRemove(id);
	}
	
}
