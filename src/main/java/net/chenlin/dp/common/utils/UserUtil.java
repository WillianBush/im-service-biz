package net.chenlin.dp.common.utils;

import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.constant.RestApiConstant;
import net.chenlin.dp.common.exception.GoLoginException;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@DependsOn("springContextUtils")
@Slf4j
public class UserUtil {

    private final  RedisCacheManager redisCacheManager = (RedisCacheManager) SpringContextUtils.getBean("redisCacheManager");

    HttpServletRequest getHttpServletRequest() {
        return WebUtils.getRequest();
    }

    public SysUserEntity getUserEntity() {
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

    public SysUserEntity getUserEntityAspect() {
        String token = getToken();
        if (token == null ) {
            log.error("getUserEntityAspect,token为空");
           return  null;
        }
        SysUserEntity sysUser = redisCacheManager.getJsonObjectFromJsonString(RedisCacheKeys.LOGIN_REDIS_CACHE+token, SysUserEntity.class);
        if (sysUser == null ) {
            log.error("getUserEntityAspect,sysUser为空,token:{}",token);
            return  null;
        }
        return sysUser;
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
