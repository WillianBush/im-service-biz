package net.chenlin.dp.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;
import net.chenlin.dp.modules.sys.entity.SysOrgEntity;
import net.chenlin.dp.modules.sys.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 组织机构
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/sys/org")
@AllArgsConstructor
@Api(tags = "站点管理")
@DependsOn("springContextUtils")
public class SysOrgController extends AbstractController {

	private SysOrgService sysOrgService;
	
	/**
	 * 机构列表
	 * @return
	 */
	@ApiOperation(value = "机构列表")
	@PostMapping("/list")
	public Page<SysOrgEntity> list(@RequestBody(required = false) Map<String, Object> params) {
		return sysOrgService.listOrg(params);
	}
	
	/**
	 * 树形机构列表，机构新增、编辑
	 * @return
	 */
	@ApiOperation(value = "树形机构列表，机构新增、编辑")
	@GetMapping("/select")
	public List<SysOrgEntity> select() {
		return sysOrgService.listOrgTree();
	}
	
	/**
	 * 新增机构
	 * @param org
	 * @return
	 */
	@SysLog("新增机构")
	@ApiOperation(value = "新增机构")
	@PostMapping("/save")
	public R save(@RequestBody SysOrgEntity org) {
		return sysOrgService.saveOrg(org);
	}
	
	/**
	 * 根据id查询机构详情
	 * @param orgId
	 * @return
	 */
	@ApiOperation(value = "根据id查询机构详情")
	@GetMapping("/info")
	public R info(@RequestBody Long orgId) {
		return sysOrgService.getOrg(orgId);
	}
	
	/**
	 * 修改机构
	 * @param org
	 * @return
	 */
	@SysLog("修改机构")
	@ApiOperation(value = "修改机构")
	@PostMapping("/update")
	public R update(@RequestBody SysOrgEntity org) {
		return sysOrgService.updateOrg(org);
	}
	
	/**
	 * 删除机构
	 * @param id
	 * @return
	 */
	@SysLog("删除机构")
	@ApiOperation(value = "删除机构")
	@PostMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return sysOrgService.bactchRemoveOrg(id);
	}
	
}
