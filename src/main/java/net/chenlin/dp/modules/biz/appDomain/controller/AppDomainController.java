package net.chenlin.dp.modules.biz.appDomain.controller;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.appDomain.entity.AppDomainEntity;
import net.chenlin.dp.modules.biz.appDomain.service.AppDomainService;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/appDomain")
public class AppDomainController extends AbstractController {
	
	@Autowired
	private AppDomainService appDomainService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<AppDomainEntity> list(@RequestBody Map<String, Object> params) {
		return appDomainService.listAppDomain(params);
	}
		
	/**
	 * 新增
	 * @param appDomain
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody AppDomainEntity appDomain) {
		SysUserEntity sysUserEntity = super.getUser();
		appDomain.setCreateBy(sysUserEntity.getUsername());
		appDomain.setUpdateBy(sysUserEntity.getUsername());
		return appDomainService.saveAppDomain(appDomain);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return appDomainService.getAppDomainById(id);
	}
	
	/**
	 * 修改
	 * @param appDomain
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody AppDomainEntity appDomain) {
		SysUserEntity sysUserEntity = super.getUser();
		appDomain.setUpdateBy(sysUserEntity.getUsername());
		return appDomainService.updateAppDomain(appDomain);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return appDomainService.batchRemove(id);
	}
	
}
