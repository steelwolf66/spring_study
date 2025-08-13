package com.zhouyu.study.week1.day1.annotation;

@SimpleAnnotation
public class AnnotationTargetEntity {
    @SimpleAnnotation(value = 2)
    private String name;
    @SimpleAnnotation(value = 3)
    public void process(){
        System.out.println("process");
    }
}
