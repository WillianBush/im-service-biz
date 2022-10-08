package net.chenlin.dp.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.constant.SystemConstant;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import net.chenlin.dp.modules.sys.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统用户
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/sys/user")
@AllArgsConstructor
@Api(tags = "后台管理员")
public class SysUserController extends AbstractController {

	private SysUserService sysUserService;
	
	/**
	 * 用户列表
	 * @param params
	 * @return
	 */
	@GetMapping("/list")
	@ApiOperation(value = "管理员列表")
	public Page<SysUserEntity> list(@RequestBody Map<String, Object> params) {
		if(getUserId() != SystemConstant.SUPER_ADMIN) {
			params.put("userIdCreate", getUserId());
		}
		return sysUserService.listUser(params);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	@ApiOperation(value = "获取登录的用户信息")
	public R info(){
		return R.ok().put("user", getUser());
	}
	
	/**
	 * 用户权限
	 * @return
	 */
	@GetMapping("/perms")
	@ApiOperation(value = "用户权限")
	public R listUserPerms() {
		return CommonUtils.msgNotCheckNull(sysUserService.listUserPerms(getUserId()));
	}
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@SysLog("新增用户")
	@PostMapping("/save")
	@ApiOperation(value = "新增用户")
	public R save(@RequestBody SysUserEntity user) {
		user.setUserIdCreate(getUserId());
		return sysUserService.saveUser(user);
	}
	
	/**
	 * 根据id查询详情
	 * @param userId
	 * @return
	 */
	@GetMapping("/infoUser")
	@ApiOperation(value = "根据id查询详情")
	public R getById(@RequestBody Long userId) {
		return sysUserService.getUserById(userId);
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@ApiOperation(value = "修改用户")
	public R update(@RequestBody SysUserEntity user) {
		return sysUserService.updateUser(user);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除用户")
	@PostMapping("/remove")
	@ApiOperation(value = "删除用户")
	public R batchRemove(@RequestBody Long[] id) {
		return sysUserService.batchRemove(id);
	}
	
	/**
	 * 用户修改密码
	 * @param pswd
	 * @param newPswd
	 * @return
	 */
	@SysLog("修改密码")
	@PostMapping("/updatePswd")
	@ApiOperation(value = "用户修改密码")
	public R updatePswdByUser(String pswd, String newPswd) {
		SysUserEntity user = getUser();
		user.setPassword(pswd);//原密码
		user.setEmail(newPswd);//邮箱临时存储新密码
		return sysUserService.updatePswdByUser(user);
	}
	
	/**
	 * 启用账户
	 * @param id
	 * @return
	 */
	@SysLog("启用账户")
	@PostMapping("/enable")
	@ApiOperation(value = "启用账户")
	public R updateUserEnable(@RequestBody Long[] id) {
		return sysUserService.updateUserEnable(id);
	}
	
	/**
	 * 禁用账户
	 * @param id
	 * @return
	 */
	@SysLog("禁用账户")
	@PostMapping("/disable")
	@ApiOperation(value = "禁用账户")
	public R updateUserDisable(@RequestBody Long[] id) {
		return sysUserService.updateUserDisable(id);
	}
	
	/**
	 * 重置密码
	 * @param user
	 * @return
	 */
	@SysLog("重置密码")
	@PostMapping("/reset")
	@ApiOperation(value = "重置密码")
	public R updatePswd(@RequestBody SysUserEntity user) {
		return sysUserService.updatePswd(user);
	}
}
