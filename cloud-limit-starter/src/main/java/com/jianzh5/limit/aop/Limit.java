package com.jianzh5.limit.aop;

import java.lang.annotation.*;

/**
 * 自定义限流注解
 * 公众号：JAVA日知录
 * @date 2023/3/15 17:25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Limit {

    String key() default "";

    int limitNum() default 10;

    int seconds() default 1;
}
