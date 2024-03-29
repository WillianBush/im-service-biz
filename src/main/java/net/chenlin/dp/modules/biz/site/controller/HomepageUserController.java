package net.chenlin.dp.modules.biz.site.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.site.entity.HomepageEntity;
import net.chenlin.dp.modules.biz.site.entity.HomepageUserEntity;
import net.chenlin.dp.modules.biz.site.service.HomepageUserService;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/site/homepageUser")
@Api(tags = "主页")
@DependsOn("springContextUtils")
public class HomepageUserController extends AbstractController {

	private HomepageUserService homepageUserService;

	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<HomepageUserEntity> list(@RequestBody Map<String, Object> params) {
		params.put("domain",getServerName());
		return homepageUserService.listHomepage(params);
	}
		
	/**
	 * 新增
	 * @param homepage
	 * @return
	 */
	@SysLog("新增")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<HomepageUserEntity> save(@RequestBody HomepageUserEntity homepage) {
		return homepageUserService.saveHomepageUser(homepage);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@PostMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<HomepageUserEntity> getById(@RequestBody String id) {
		return homepageUserService.getHomepageById(id);
	}
	
	/**
	 * 修改
	 * @param homepageUserEntity
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody HomepageUserEntity homepageUserEntity) {
		return homepageUserService.updateHomepage(homepageUserEntity);
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
		return homepageUserService.batchRemove(id);
	}
	
}
