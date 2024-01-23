package net.chenlin.dp.modules.biz.appVersion.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.appVersion.entity.AppVersionEntity;
import net.chenlin.dp.modules.biz.appVersion.service.AppVersionService;

/**
 * app版本升级
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/appVersion")
@Api(tags = "app版本升级")
@DependsOn("springContextUtils")
public class AppVersionController extends AbstractController {
	

	private AppVersionService appVersionService;

	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<AppVersionEntity> list(@RequestBody Map<String, Object> params) {
		return appVersionService.listAppVersion(params);
	}
		
	/**
	 * 新增
	 * @param appVersion
	 * @return
	 */
	@SysLog("新增app版本升级")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<AppVersionEntity> save(@RequestBody AppVersionEntity appVersion) {
		if (null == appVersion) {
			return  Resp.error("参数异常");
		}

		if (StringUtils.isEmpty(appVersion.getAppId())
				|| StringUtils.isEmpty(appVersion.getDownUrl())
				|| StringUtils.isEmpty(appVersion.getDeviceType())
		        || StringUtils.isEmpty(appVersion.getVersion())){
			return Resp.error("参数异常");
		}
		if (appVersion.getOrgId() == null){
			appVersion.setOrgId(1L);
		}
		AppVersionEntity appVersionEntity = appVersionService.selectByUniqueKey(appVersion.getVersion(),appVersion.getOrgId(),appVersion.getDeviceType(),appVersion.getAppId());
		if (null != appVersionEntity) {
			return Resp.error("版本已存在，请核对");
		}
		return appVersionService.saveAppVersion(appVersion,getServerName());
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@PostMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<AppVersionEntity> getById(@RequestBody Long id) {
		return appVersionService.getAppVersionById(id);
	}
	
	/**
	 * 修改
	 * @param appVersion
	 * @return
	 */
	@SysLog("修改app版本升级")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody AppVersionEntity appVersion) {
		if (null == appVersion.getId()) {
			return Resp.error("id 不可为空");
		}
		return appVersionService.updateAppVersion(appVersion);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除app版本升级")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp<Integer> batchRemove(@RequestBody Long[] id) {
		return appVersionService.batchRemove(id);
	}


	@PostMapping("/appInfo")
	@ApiOperation(value = "根据OS,AppName查询详情")
	public Resp<AppVersionEntity> getByOSAndAppName(@RequestBody AppVersionEntity appVersion) {
		return appVersionService.getAppVersionByOSAndAppName(appVersion.getDeviceType(), appVersion.getAppName());
	}
}
