package net.chenlin.dp.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * @author wang<fangyuan.co@outlook.com>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";
}
