package net.chenlin.dp.common.aspect;

import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.common.utils.JSONUtils;
import net.chenlin.dp.common.utils.UserUtil;
import net.chenlin.dp.common.utils.WebUtils;
import net.chenlin.dp.modules.sys.dao.SysLogMapper;
import net.chenlin.dp.modules.sys.entity.SysLogEntity;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.lang.reflect.Method;


/**
 * 系统日志，切面处理类
 * @author wang<fangyuan.co@outlook.com>
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {
	
	@Resource
	private SysLogMapper sysLogMapper;

	@Resource
	private UserUtil userUtil;
	
	@Pointcut("@annotation(net.chenlin.dp.common.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		if (null == point) {
			return null;
		}
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		//保存日志
		saveSysLog(point, time);
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}
		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = JSONUtils.beanToJson(args[0]);
			sysLog.setParams(params);
		}catch (Exception e){
			log.error("",e);
		}
		//设置IP地址
		sysLog.setIp(WebUtils.getIpAddr());
		//用户名
		SysUserEntity currUser = userUtil.getUserEntityAspect();
		if(null == currUser ) {
			sysLog.setUserId(-1L);
			sysLog.setUsername("未知用户");
			return;
		} else {
			if(sysLog.getParams() != null) {
				sysLog.setUserId(currUser.getUserId());
				sysLog.setUsername(currUser.getUsername());
			} else {
				sysLog.setUserId(-1L);
				sysLog.setUsername("获取用户信息为空");
				return;
			}
		}
		sysLog.setTime(time);
		//保存系统日志
		sysLogMapper.save(sysLog);
	}
	
}
