package net.chenlin.dp.modules.biz.appBase.controller;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.appBase.entity.AppBaseEntity;
import net.chenlin.dp.modules.biz.appBase.service.AppBaseService;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * app基本信息
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/appBase")
public class AppBaseController extends AbstractController {
	
	@Autowired
	private AppBaseService appBaseService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<AppBaseEntity> list(@RequestBody Map<String, Object> params) {
		return appBaseService.listAppBase(params);
	}
		
	/**
	 * 新增
	 * @param appBase
	 * @return
	 */
	@SysLog("新增app基本信息")
	@RequestMapping("/save")
	public R save(@RequestBody AppBaseEntity appBase) {
		SysUserEntity sysUserEntity = super.getUser();
		appBase.setCreateBy(sysUserEntity.getUsername());
		appBase.setUpdateBy(sysUserEntity.getUsername());
		return appBaseService.saveAppBase(appBase);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return appBaseService.getAppBaseById(id);
	}
	
	/**
	 * 修改
	 * @param appBase
	 * @return
	 */
	@SysLog("修改app基本信息")
	@RequestMapping("/update")
	public R update(@RequestBody AppBaseEntity appBase) {
		SysUserEntity sysUserEntity = super.getUser();
		appBase.setUpdateBy(sysUserEntity.getUsername());
		return appBaseService.updateAppBase(appBase);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除app基本信息")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return appBaseService.batchRemove(id);
	}
	
}
