package net.chenlin.dp.common.utils;

import net.chenlin.dp.common.support.shiro.listener.UserSessionListener;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Shiro工具类
 * @author wang<fangyuan.co@outlook.com>
 */
public class ShiroUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionListener.class);

	/**
	 * 获取session
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 获取当前用户
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前用户信息
	 * @return
	 */
	public static SysUserEntity getUserEntity() {
		Object object = SecurityUtils.getSubject().getPrincipal();
		if (object instanceof SysUserEntity) {
			return (SysUserEntity) object;
		}else {
			logout();
			return null;
		}
	}

	/**
	 * 获取当前登录用户id
	 * @return
	 */
	public static Long getUserId() {
		return getUserEntity().getUserId();
	}

	/**
	 * 设置session域参数
	 * @param key
	 * @param value
	 */
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 获取session域参数
	 * @param key
	 * @return
	 */
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 是否登录
	 * @return
	 */
	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	/**
	 * 登出
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}

	/**
	 * 获取验证码
	 * @param key
	 * @return
	 */
	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}

}
