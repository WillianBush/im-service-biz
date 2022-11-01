package net.chenlin.dp.common.support.interceptor;

import net.chenlin.dp.common.annotation.RestAnon;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.constant.RestApiConstant;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.common.utils.JSONUtils;
import net.chenlin.dp.common.utils.SpringContextUtils;
import net.chenlin.dp.common.utils.WebUtils;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import net.chenlin.dp.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * rest api拦截器
 * @author wang<fangyuan.co@outlook.com>
 */
@DependsOn("springContextUtils")
public class RestApiInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(RestApiInterceptor.class);

    private final SysUserService userService = (SysUserService) SpringContextUtils.getBean("sysUserService");

    private final RedisCacheManager redisCacheManager = (RedisCacheManager) SpringContextUtils.getBean("redisCacheManager");


    /**
     * 拦截
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 静态资源请求拦截
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        // 有RestAnon注解的方法不拦截
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.hasMethodAnnotation(RestAnon.class)) {
                return true;
            }
        }

        if(request.getMethod().equals(HttpMethod.OPTIONS.name())){
            log.info("OPTIONS直接放行");
            response.setStatus(HttpStatus.SC_OK);
            return true;
        }
        return checkToken(request, response);
    }

    /**
     * token校验
     * @param request
     * @param response
     * @return
     */
    private boolean checkToken(HttpServletRequest request, HttpServletResponse response) {
        // 登录 或 有效状态校验 请求直接通过
        String requestPath = request.getServletPath();
        if (RestApiConstant.AUTH_REQUEST.equals(requestPath) || RestApiConstant.AUTH_CHECK.equals(requestPath) || requestPath.contains("swagger")) {
            return true;
        }
        // 校验请求是否包含验证信息
        String token = getToken(request);
//        log.info("token:{}",token);
        if (StringUtils.isBlank(token)) {
            WebUtils.write(response, JSONUtils.beanToJson(RestApiConstant.TokenErrorEnum.TOKEN_NOT_FOUND.getResp()));
            return false;
        }
        try {
            SysUserEntity sysUser = redisCacheManager.getJsonObjectFromJsonString(RedisCacheKeys.LOGIN_REDIS_CACHE+token, SysUserEntity.class);
            if (sysUser == null ) {
                WebUtils.write(response, JSONUtils.beanToJson(RestApiConstant.TokenErrorEnum.TOKEN_EXPIRED.getResp()));
                return false;
            }
        } catch (Exception e) {
            log.info("token解析异常：{}", token);
            return false;
        }
        return true;
    }

    /**
     * 获取token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        // 请求头token
        String token = request.getHeader(RestApiConstant.AUTH_TOKEN);
        if (StringUtils.isBlank(token)) {
            // 请求参数token
            return request.getParameter(RestApiConstant.AUTH_TOKEN);
        }
        return token;
    }

}
