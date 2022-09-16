package net.chenlin.dp.modules.biz.appResigned.controller;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.appResigned.entity.AppResignedEntity;
import net.chenlin.dp.modules.biz.appResigned.service.AppResignedService;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 重签后的app信息
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/appResign")
public class AppResignedController extends AbstractController {
	
	@Autowired
	private AppResignedService appResignedService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<AppResignedEntity> list(@RequestBody Map<String, Object> params) {
		return appResignedService.listAppResigned(params);
	}
		
	/**
	 * 新增
	 * @param appResigned
	 * @return
	 */
	@SysLog("新增重签后的app信息")
	@RequestMapping("/save")
	public R save(@RequestBody AppResignedEntity appResigned) {
		SysUserEntity sysUserEntity = super.getUser();
		appResigned.setCreateBy(sysUserEntity.getUsername());
		appResigned.setUpdateBy(sysUserEntity.getUsername());
		return appResignedService.saveAppResigned(appResigned);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return appResignedService.getAppResignedById(id);
	}
	
	/**
	 * 修改
	 * @param appResigned
	 * @return
	 */
	@SysLog("修改重签后的app信息")
	@RequestMapping("/update")
	public R update(@RequestBody AppResignedEntity appResigned) {
		return appResignedService.updateAppResigned(appResigned);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除重签后的app信息")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return appResignedService.batchRemove(id);
	}
	
}
