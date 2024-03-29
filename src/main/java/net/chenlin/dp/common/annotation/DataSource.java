package net.chenlin.dp.common.annotation;

import java.lang.annotation.*;

/**
 * 数据源注解
 * @author wang<fangyuan.co@outlook.com>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String value() default "";

}
