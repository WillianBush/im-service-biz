package net.chenlin.dp.modules.biz.domain.controller;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.domain.entity.DomainEntity;
import net.chenlin.dp.modules.biz.domain.service.DomainService;
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
@RequestMapping("/domain")
public class DomainController extends AbstractController {
	
	@Autowired
	private DomainService domainService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<DomainEntity> list(@RequestBody Map<String, Object> params) {
		return domainService.listDomain(params);
	}
		
	/**
	 * 新增
	 * @param domain
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody DomainEntity domain) {
		SysUserEntity user = super.getUser();
		domain.setCreateBy(user.getUsername());
		domain.setUpdateBy(user.getUsername());
		return domainService.saveDomain(domain);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return domainService.getDomainById(id);
	}
	
	/**
	 * 修改
	 * @param domain
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody DomainEntity domain) {
		SysUserEntity user = super.getUser();
		domain.setUpdateBy(user.getUsername());
		return domainService.updateDomain(domain);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return domainService.batchRemove(id);
	}
	
}
