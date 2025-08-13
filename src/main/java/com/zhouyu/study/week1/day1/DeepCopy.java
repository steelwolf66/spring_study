package com.zhouyu.study.week1.day1;

import java.io.*;

/**
 * 深拷贝
 * 类要实现可序列化接口，不然会报错
 * 深拷贝的原理：将当前对象转变为对象流后，再将对象流转变味字节流，之后再将字节流转变为对象
 * 实际上就是一个序列化反序列化的过程，只不过这中间产生的这个新的对象，就是深拷贝的对象
 */
public class DeepCopy implements Serializable {
    private String name ;
    private int age ;


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

    public DeepCopy deepCopy() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)){
            objectOutputStream.writeObject(this);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        try(ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)){
            return (DeepCopy) objectInputStream.readObject();
        }
    }

    public static void main(String[] args) {
        DeepCopy deepCopy = new DeepCopy();
        deepCopy.setName("a");
        deepCopy.setAge(1);
        try {
            DeepCopy deepCopy1 = deepCopy.deepCopy();
            System.out.println(deepCopy.getName());
            System.out.println(deepCopy.getAge());
            System.out.println(deepCopy1.getName());
            System.out.println(deepCopy1.getAge());
            System.out.println(deepCopy.equals(deepCopy1));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
