package net.chenlin.dp.modules.sys.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.constant.RestApiConstant;
import net.chenlin.dp.common.constant.SystemConstant;
import net.chenlin.dp.common.exception.GoLoginException;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.common.utils.JSONUtils;
import net.chenlin.dp.common.utils.SpringContextUtils;
import net.chenlin.dp.common.utils.WebUtils;
import net.chenlin.dp.modules.sys.entity.SysRoleEntity;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import net.chenlin.dp.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller公共组件
 * @author wang<fangyuan.co@outlook.com>
 */
@Slf4j
public abstract class AbstractController {


	protected final RedisCacheManager redisCacheManager = (RedisCacheManager) SpringContextUtils.getBean("redisCacheManager");

	/**
	 * 获取参数
	 * @param name 请求参数名称
	 * @return 请求参数值
	 */
	protected String getParam(String name) {
		return WebUtils.getRequest().getParameter(name);
	}

	/**
	 * 设置属性
	 * @param key 属性名
	 * @param value 属性值
	 */
	protected void setAttr(String key, Object value) {
		WebUtils.getRequest().setAttribute(key, value);
	}

	/**
	 * 获取httpServletRequest
	 * @return HttpServletRequest
	 */
	protected HttpServletRequest getHttpServletRequest() {
		return WebUtils.getRequest();
	}

	/**
	 * 获取httpServletResponse
	 * @return HttpServletResponse
	 */
	protected HttpServletResponse getHttpServletResponse() {
		return WebUtils.getResponse();
	}

	/**
	 * 获取session：如果当前请求没有session，则创建一个
	 * @return HttpSession
	 */
	protected HttpSession getSession() {
		return WebUtils.getRequest().getSession();
	}

	/**
	 * 获取session：如果当前请求没有session，true则创建一个，false则返回null
	 * @param create 是否创建，true：创建，false：不创建，返回null
	 * @return HttpSession
	 */
	protected HttpSession getSession(boolean create) {
		return WebUtils.getRequest().getSession(create);
	}

	/**
	 * 获取当前用户entity
	 * @return SysUserEntity
	 */
	protected SysUserEntity getUser() {
		String token = getToken();
		if (token == null ) {
			throw new GoLoginException("登录过期",1001);
		}
		SysUserEntity sysUser = redisCacheManager.getJsonObjectFromJsonString(RedisCacheKeys.LOGIN_REDIS_CACHE+token, SysUserEntity.class);
		if (sysUser == null ) {
			throw new GoLoginException("登录过期",1001);
		}
		return sysUser;
	}

	/**
	 * 获取当前用户id
	 * @return 用户id
	 */
	protected Long getUserId() {
		SysUserEntity user =getUser();
		if (user == null) {
			throw new GoLoginException("请重新登陆");
		}
		return user.getUserId();
	}

	protected boolean isSuperAdmin(){
		SysUserEntity user = getUser();
		for ( SysRoleEntity role: user.getRoleList()) {
			if (role.getRoleId().equals(SystemConstant.SUPER_ADMIN)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 重定向
	 * @param page
	 * @return 重定向全路径
	 */
	protected String redirect(String page) {
		return "redirect:".concat(page);
	}

	/**
	 * beetl视图
	 * @param page
	 * @return html全路径
	 */
	protected String html(String page) {
		return page.concat(".html");
	}


	private String getToken() {
		// 请求头token
		String token =  getHttpServletRequest().getHeader(RestApiConstant.AUTH_TOKEN);
		if (StringUtils.isBlank(token)) {
			// 请求参数token
			return  getHttpServletRequest().getParameter(RestApiConstant.AUTH_TOKEN);
		}
		return token;
	}
}
