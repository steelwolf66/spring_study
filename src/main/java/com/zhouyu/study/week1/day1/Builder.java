package com.zhouyu.study.week1.day1;

/**
 * 建造者模式
 * 可以解决多个参数，需要多个构造器问题，很好的扩展需要多个可选参数
 * 让类的创建和表示分离
 * 使相同的创建过程，可以创建不同的表示
 *
 * 缺点：
 * 创建对象前先要创建builder对象，极端情况下增加性能开销
 * 比伸缩构造方法更冗长，建议参数多于4个的时候，再使用这种建造者模式进行构造对象
 */
public class Builder {
    String name;
    int age;

    public Builder name(String name){
        this.name = name;
        return this;
    }

    public Builder age(int age){
        this.age = age;
        return this;
    }

    public Person build(){
        return new Person(this);
    }

    public static void main(String[] args) {
        Builder builder = new Builder();
        Person james = builder.name("james").age(18).build();
    }
}

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public Person(Builder builder){
        this.name = builder.name;
        this.age = builder.age;
    }
}
