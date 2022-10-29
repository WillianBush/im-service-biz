package net.chenlin.dp.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.constant.RestApiConstant;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.exception.GoLoginException;
import net.chenlin.dp.common.utils.MD5Utils;
import net.chenlin.dp.common.utils.TokenUtils;
import net.chenlin.dp.common.utils.WebUtils;
import net.chenlin.dp.modules.sys.dao.SysUserRoleMapper;
import net.chenlin.dp.modules.sys.entity.SysLoginEntity;
import net.chenlin.dp.modules.sys.entity.SysLoginResp;
import net.chenlin.dp.modules.sys.entity.SysRoleEntity;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import net.chenlin.dp.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户controller
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@Slf4j
@AllArgsConstructor
@Api(tags = "管理后台登陆")
@DependsOn("springContextUtils")
public class SysLoginController extends AbstractController {

	private SysUserService sysUserService;

	SysUserRoleMapper sysUserRoleMapper;

	/**
	 * 登录
	 */
	@SysLog("登录")
	@PostMapping(value = "/login")
	@ApiOperation(value = "用户登录")
	public Resp<SysLoginResp> login(@RequestBody SysLoginEntity user) {
		try {
			// 开启验证码
			// 用户名验证
			if (StringUtils.isBlank(user.getUsername())) {
				throw new GoLoginException("用户名不能为空",1001);
			}
			// 密码验证
			if (StringUtils.isBlank(user.getPassword())) {
				throw  new GoLoginException("密码不能为空",1001);
			}
			String password = MD5Utils.encrypt(user.getUsername(), user.getPassword());
			SysUserEntity userEntity = sysUserService.login(user.getUsername(),password);
			if (userEntity == null ) {
				return Resp.error(1001,"账号或密码错误");
			}

			if (userEntity.getStatus() == null || userEntity.getStatus().equals(0)){
				return Resp.error(1001,"该账号已经禁用");
			}

			if( userEntity.getEnableGoogleKaptcha().equals(1) ){

				if ( StringUtils.isEmpty(user.getGoogleCode()) || !sysUserService.checkGoogleKaptcha(user.getUsername(),user.getGoogleCode())) {
					return  Resp.error(1001,"谷歌验证码错误");
				}
			}

			SysLoginResp resp = new SysLoginResp();
			userEntity.setPassword("");
			userEntity.setGoogleKaptchaKey("");
			userEntity.setEnableGoogleKaptcha(null);
			resp.setSysUserEntity(userEntity);
			String token = TokenUtils.generateValue(userEntity.getUsername());
			List<SysRoleEntity>  roleSigns= new ArrayList<>(sysUserService.listUserRoleList(userEntity.getUserId()));
			userEntity.setRoleList(roleSigns);
			userEntity.setLast_login_ip(WebUtils.getIpAddr());
			sysUserService.saveOrUpdateToken(userEntity,token);
			getHttpServletRequest().getSession().setAttribute(RestApiConstant.AUTH_TOKEN,token);
			getHttpServletRequest().getSession().setAttribute(RestApiConstant.AUTH_USER,userEntity);
			resp.setToken(token);
			return Resp.ok("验证成功",resp);
		} catch (Exception e) {
			log.error("login, 登录异常 user:{}",user,e);
		}
		return  Resp.error(1001,"登录服务异常");
	}


	/**
	 * 退出
	 */
	@SysLog("退出系统")
	@PostMapping(value = "/logout")
	@ApiOperation(value = "退出登录")
	public Resp logout() {
		redisCacheManager.del(RedisCacheKeys.LOGIN_REDIS_CACHE + getToken());
		return  Resp.ok(200,"成功退出");
	}

	private String getToken() {
		// 请求头token
		String token =  getHttpServletRequest().getHeader(RestApiConstant.AUTH_TOKEN);
		if (StringUtils.isBlank(token)) {
			// 请求参数token
			token =  getHttpServletRequest().getParameter(RestApiConstant.AUTH_TOKEN);

			if (token == null) {
				token = (String) getHttpServletRequest().getSession().getAttribute(RestApiConstant.AUTH_TOKEN);
			}
		}
		return token;
	}

}
