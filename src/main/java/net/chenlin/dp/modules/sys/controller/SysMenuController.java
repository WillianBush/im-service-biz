package net.chenlin.dp.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.sys.entity.SysMenuEntity;
import net.chenlin.dp.modules.sys.service.SysMenuService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单controller
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/sys/menu")
@Api(tags = "系统菜单")
@DependsOn("springContextUtils")
public class SysMenuController extends AbstractController {

	@Resource
	private SysMenuService sysMenuService;
	

	@GetMapping("/user")
	@ApiOperation(value = "查询用户权限菜单")
	public Resp<SysMenuEntity> user(){
		return sysMenuService.listUserMenu(getUserId());
	}
	
	/**
	 * 菜单列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "菜单列表")
	public List<SysMenuEntity> listMenu(@RequestParam Map<String, Object> params) {
		return sysMenuService.listMenu(params);
	}
	
	/**
	 * 选择菜单(添加、修改)
	 * @return
	 */
	@GetMapping("/select")
	@ApiOperation(value = "选择菜单(添加、修改)")
	public Resp<SysMenuEntity> select() {
		return sysMenuService.listNotButton();
	}
	
	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	@SysLog("新增菜单")
	@PostMapping("/save")
	@ApiOperation(value = "新增菜单")
	public Resp<Integer> save(@RequestBody SysMenuEntity menu) {
		return sysMenuService.saveMenu(menu);
	}

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "查询详情")
	public Resp<SysMenuEntity> info(@RequestBody Long id) {
		return sysMenuService.getMenuById(id);
	}
	
	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	@SysLog("修改菜单")
	@PostMapping("/update")
	@ApiOperation(value = "修改菜单")
	public Resp<Integer> update(@RequestBody SysMenuEntity menu) {
		return sysMenuService.updateMenu(menu);
	}
	
	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@SysLog("删除菜单")
	@PostMapping("/remove")
	@ApiOperation(value = "删除菜单")
	public Resp remove(@RequestBody Long[] id) {
		return sysMenuService.batchRemove(id);
	}
	
}
