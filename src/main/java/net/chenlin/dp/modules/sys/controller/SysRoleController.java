package net.chenlin.dp.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.constant.SystemConstant;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.sys.entity.SysRoleEntity;
import net.chenlin.dp.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统角色
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "系统角色")
@AllArgsConstructor
public class SysRoleController extends AbstractController {

	private SysRoleService sysRoleService;
	
	/**
	 * 角色列表
	 * @param params
	 * @return
	 */
	@GetMapping("/list")
	@ApiOperation(value = "角色列表")
	public Page<SysRoleEntity> list(@RequestBody Map<String, Object> params) {
		if(getUserId() != SystemConstant.SUPER_ADMIN) {
			params.put("userIdCreate", getUserId());
		}
		return sysRoleService.listRole(params);
	}
	
	/**
	 * 用户角色
	 * @return
	 */
	@GetMapping("/select")
	@ApiOperation(value = "用户角色")
	public List<SysRoleEntity> listRole() {
		return sysRoleService.listRole();
	}
	
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	@SysLog("新增角色")
	@PostMapping("/save")
	@ApiOperation(value = "新增角色")
	public Resp<Integer> saveRole(@RequestBody SysRoleEntity role) {
		role.setUserIdCreate(getUserId());
		return sysRoleService.saveRole(role);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public  Resp<SysRoleEntity> getRoleById(@RequestBody Long id) {
		return sysRoleService.getRoleById(id);
	}
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@SysLog("修改角色")
	@PostMapping("/update")
	@ApiOperation(value = "修改角色")
	public Resp<Integer> updateRole(@RequestBody SysRoleEntity role) {
		return sysRoleService.updateRole(role);
	}
	
	/**
	 * 批量删除
	 * @param id
	 * @return
	 */
	@SysLog("删除角色")
	@PostMapping("/remove")
	@ApiOperation(value = "批量删除")
	public Resp batchRemove(@RequestBody Long[] id) {
		return sysRoleService.batchRemove(id);
	}
	
	/**
	 * 分配权限
	 * @param role
	 * @return
	 */
	@SysLog("操作权限")
	@PostMapping("/authorize/opt")
	@ApiOperation(value = "分配权限")
	public Resp<Integer> updateRoleOptAuthorization(@RequestBody SysRoleEntity role) {
		return sysRoleService.updateRoleOptAuthorization(role);
	}
	
	/**
	 * 数据权限
	 * @param role
	 * @return
	 */
	@SysLog("数据权限")
	@GetMapping("/authorize/data")
	@ApiOperation(value = "数据权限")
	public  Resp<Integer> updateRoleDataAuthorization(@RequestBody SysRoleEntity role) {
		return sysRoleService.updateRoleDataAuthorization(role);
	}
	
}
