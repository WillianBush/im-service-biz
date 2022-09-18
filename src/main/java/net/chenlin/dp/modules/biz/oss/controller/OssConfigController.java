package net.chenlin.dp.modules.biz.oss.controller;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.oss.entity.OssConfigEntity;
import net.chenlin.dp.modules.biz.oss.service.OssConfigService;
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
@RequestMapping("/oss")
public class OssConfigController extends AbstractController {
	
	@Autowired
	private OssConfigService ossConfigService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<OssConfigEntity> list(@RequestBody Map<String, Object> params) {
		return ossConfigService.listOssConfig(params);
	}
		
	/**
	 * 新增
	 * @param ossConfig
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody OssConfigEntity ossConfig) {
		SysUserEntity user = super.getUser();
		ossConfig.setCreateBy(user.getUsername());
		ossConfig.setUpdateBy(user.getUsername());
		return ossConfigService.saveOssConfig(ossConfig);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return ossConfigService.getOssConfigById(id);
	}
	
	/**
	 * 修改
	 * @param ossConfig
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody OssConfigEntity ossConfig) {
		SysUserEntity user = super.getUser();
		ossConfig.setUpdateBy(user.getUsername());
		return ossConfigService.updateOssConfig(ossConfig);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return ossConfigService.batchRemove(id);
	}
	
}
