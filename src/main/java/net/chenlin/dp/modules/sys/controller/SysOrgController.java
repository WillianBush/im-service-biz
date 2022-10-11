package net.chenlin.dp.modules.sys.controller;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.sys.entity.SysOrgEntity;
import net.chenlin.dp.modules.sys.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 组织机构
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/sys/org")
@AllArgsConstructor
@DependsOn("springContextUtils")
public class SysOrgController extends AbstractController {

	private SysOrgService sysOrgService;
	
	/**
	 * 机构列表
	 * @return
	 */
	@RequestMapping("/list")
	public List<SysOrgEntity> list() {
		return sysOrgService.listOrg();
	}
	
	/**
	 * 树形机构列表，机构新增、编辑
	 * @return
	 */
	@RequestMapping("/select")
	public List<SysOrgEntity> select() {
		return sysOrgService.listOrgTree();
	}
	
	/**
	 * 新增机构
	 * @param org
	 * @return
	 */
	@SysLog("新增机构")
	@RequestMapping("/save")
	public R save(@RequestBody SysOrgEntity org) {
		return sysOrgService.saveOrg(org);
	}
	
	/**
	 * 根据id查询机构详情
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/info")
	public R info(@RequestBody Long orgId) {
		return sysOrgService.getOrg(orgId);
	}
	
	/**
	 * 修改机构
	 * @param org
	 * @return
	 */
	@SysLog("修改机构")
	@RequestMapping("/update")
	public R update(@RequestBody SysOrgEntity org) {
		return sysOrgService.updateOrg(org);
	}
	
	/**
	 * 删除机构
	 * @param id
	 * @return
	 */
	@SysLog("删除机构")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return sysOrgService.bactchRemoveOrg(id);
	}
	
}
