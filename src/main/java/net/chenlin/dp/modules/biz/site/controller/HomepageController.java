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
import net.chenlin.dp.modules.biz.site.entity.HomepageEntity;
import net.chenlin.dp.modules.biz.site.service.HomepageService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/site/homepage")
@Api(tags = "主页")
@DependsOn("springContextUtils")
public class HomepageController extends AbstractController {
	

	private HomepageService homepageService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<HomepageEntity> list(@RequestBody Map<String, Object> params) {
		return homepageService.listHomepage(params);
	}
		
	/**
	 * 新增
	 * @param homepage
	 * @return
	 */
	@SysLog("新增")
	@GetMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<HomepageEntity> save(@RequestBody HomepageEntity homepage) {
		return homepageService.saveHomepage(homepage);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<HomepageEntity> getById(@RequestBody String id) {
		return homepageService.getHomepageById(id);
	}
	
	/**
	 * 修改
	 * @param homepage
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody HomepageEntity homepage) {
		return homepageService.updateHomepage(homepage);
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
		return homepageService.batchRemove(id);
	}
	
}
