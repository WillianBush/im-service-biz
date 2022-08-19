package net.chenlin.dp.common.annotation;

import java.lang.annotation.*;

/**
 * rest接口不需要授权注解
 * @author wang<fangyuan.co@outlook.com>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestAnon {
}
