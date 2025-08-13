package com.zhouyu.study.week1.day1.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 一般都是在AOP中使用
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.TYPE})
public @interface SimpleAnnotation {
    int value() default 1;
}
