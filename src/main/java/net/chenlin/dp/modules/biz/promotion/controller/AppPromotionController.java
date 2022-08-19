package net.chenlin.dp.modules.biz.promotion.controller;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.promotion.entity.AppPromotionEntity;
import net.chenlin.dp.modules.biz.promotion.service.AppPromotionService;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 域名URL
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/promotion")
@AllArgsConstructor
public class AppPromotionController extends AbstractController {
	

	private AppPromotionService appPromotionService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<AppPromotionEntity> list(@RequestBody Map<String, Object> params) {
		return appPromotionService.listAppPromotion(params);
	}
		
	/**
	 * 新增
	 * @param appPromotion
	 * @return
	 */
	@SysLog("新增域名URL")
	@RequestMapping("/save")
	public R save(@RequestBody AppPromotionEntity appPromotion) {
		return appPromotionService.saveAppPromotion(appPromotion);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return appPromotionService.getAppPromotionById(id);
	}
	
	/**
	 * 修改
	 * @param appPromotion
	 * @return
	 */
	@SysLog("修改域名URL")
	@RequestMapping("/update")
	public R update(@RequestBody AppPromotionEntity appPromotion) {
		return appPromotionService.updateAppPromotion(appPromotion);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除域名URL")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return appPromotionService.batchRemove(id);
	}


	/**
	 *
	 * @param appName
	 * @return
	 */
	@SysLog("申请推广链接")
	@RequestMapping("/applyUrl")
	public R applyUrl(@RequestParam(value = "appName") String appName) {
		SysUserEntity user = super.getUser();
		return appPromotionService.applyUrl(appName,user);
	}
	
}
