package com.zhouyu.study.week1.day1.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 自定义注解处理
 */
public class AnnotationAProcessor {
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        //解析作用在类上的注解
        Class<AnnotationTargetEntity> annotationTargetEntityClass = AnnotationTargetEntity.class;
        SimpleAnnotation annotation = annotationTargetEntityClass.getAnnotation(SimpleAnnotation.class);
        System.out.println("type value:" + annotation.value());
        //解析 作用在方法
        Method process = annotationTargetEntityClass.getDeclaredMethod("process");
        if(process.isAnnotationPresent(SimpleAnnotation.class)){
            int value = process.getAnnotation(SimpleAnnotation.class).value();
            System.out.println("method value:" + value);
        }
        //解析属性上的注解
        Field name = annotationTargetEntityClass.getDeclaredField("name");
        if(name.isAnnotationPresent(SimpleAnnotation.class)){
            int value = name.getAnnotation(SimpleAnnotation.class).value();
            System.out.println("field value:" + value);
        }
    }
}
