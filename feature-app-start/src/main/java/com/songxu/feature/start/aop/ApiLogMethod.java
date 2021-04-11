package com.songxu.feature.start.aop;

import java.lang.annotation.*;

/**
 * @author songxu
 * @version 1.0.0
 * @date 2021-04-11 11:14:27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ApiLogMethod {

    /**
     * 记录入参
     */
    boolean logArgs() default true;

    /**
     * 记录返回值
     */
    boolean logRetValue() default false;
}
