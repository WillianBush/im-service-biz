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
import net.chenlin.dp.modules.biz.site.entity.DefaultEmoticonEntity;
import net.chenlin.dp.modules.biz.site.service.DefaultEmoticonService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/site/defaultemoticon")
@Api(tags = "默认表情")
@DependsOn("springContextUtils")
public class DefaultEmoticonController extends AbstractController {
	

	private DefaultEmoticonService defaultEmoticonService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<DefaultEmoticonEntity> list(@RequestBody Map<String, Object> params) {
		return defaultEmoticonService.listDefaultEmoticon(params);
	}
		
	/**
	 * 新增
	 * @param defaultEmoticon
	 * @return
	 */
	@SysLog("新增")
	@GetMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<Integer> save(@RequestBody DefaultEmoticonEntity defaultEmoticon) {
		return defaultEmoticonService.saveDefaultEmoticon(defaultEmoticon);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<DefaultEmoticonEntity> getById(@RequestBody String id) {
		return defaultEmoticonService.getDefaultEmoticonById(id);
	}
	
	/**
	 * 修改
	 * @param defaultEmoticon
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody DefaultEmoticonEntity defaultEmoticon) {
		return defaultEmoticonService.updateDefaultEmoticon(defaultEmoticon);
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
		return defaultEmoticonService.batchRemove(id);
	}
	
}
