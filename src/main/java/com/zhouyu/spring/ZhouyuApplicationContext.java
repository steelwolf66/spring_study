package com.zhouyu.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ZhouyuApplicationContext {
    private Class configClass;

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> singleTonObjects = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public ZhouyuApplicationContext(Class configClass) {
        this.configClass = configClass;

        //扫描 --> 生成BeanDefinition --> beanDefinitionMap
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            //扫描路径 com.zhouyu.service
            String path = componentScanAnnotation.value();
            path = path.replace(".", "/");// com/zhouyu/service

            ClassLoader classLoader = ZhouyuApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            System.out.println(file);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    //文件的绝对路径
                    String fileName = f.getAbsolutePath();
                    System.out.println(fileName);
                    if (fileName.endsWith(".class")) {
                        String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                        className = className.replace("\\", ".");
                        try {
                            //利用反射，获取该class有没有Component注解
                            Class<?> clazz = classLoader.loadClass(className);
                            if (clazz.isAnnotationPresent(Component.class)) {
                                //是否实现了这个接口
                                if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                    BeanPostProcessor instance = (BeanPostProcessor) clazz.newInstance();
                                    beanPostProcessorList.add(instance);
                                }

                                Component componentAnnotation = clazz.getAnnotation(Component.class);
                                String beanName = componentAnnotation.value();
                                //beanName为空时，默认首字母小写
                                if ("".equals(beanName)) {
                                    beanName = Introspector.decapitalize(clazz.getSimpleName());
                                }
                                //生成BeanDefinition对象
                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setType(clazz);
                                //发现Bean ， 判断 单例Bean 还是 多例Bean，默认单例
                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                    String scopeValue = scopeAnnotation.value();
                                    beanDefinition.setScope(scopeValue);
                                } else {
                                    beanDefinition.setScope("singleton");
                                }
                                beanDefinitionMap.put(beanName, beanDefinition);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        //扫描后，创建单例Bean （实例化单例Bean）
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")) {
                Object baen = createBaen(beanName, beanDefinition);
                singleTonObjects.put(beanName, baen);
            }
        }
    }

    /**
     * 创建Bean
     *
     * @param beanName
     * @param beanDefinition
     * @return
     */
    private Object createBaen(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();
        //利用反射，获取构造方法
        try {
            //实例化
            Object instance = clazz.getConstructor().newInstance();
            //依赖注入
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    declaredField.setAccessible(true);
                    declaredField.set(instance, getBean(declaredField.getName()));
                }
            }
            //aware回调
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBaenName(beanName);
            }
            //BeanPostProcessor 初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                beanPostProcessor.postProcessBeforeInitialization(beanName, instance);
            }
            //初始化
            if (instance instanceof InitializingBean) {
                ((InitializingBean) instance).afterPropertiesSet();
            }
            //BeanPostProcessor 初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(beanName, instance);
            }

            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Bean
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        //beanName
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new NullPointerException();
        } else {
            String scope = beanDefinition.getScope();
            //单例
            if (scope.equals("singleton")) {
                Object bean = singleTonObjects.get(beanName);
                if (bean == null) {
                    Object beanNew = createBaen(beanName, beanDefinition);
                    singleTonObjects.put(beanName, beanNew);
                    return beanNew;
                } else {
                    return bean;
                }
                //多例
            } else {
                return createBaen(beanName, beanDefinition);
            }
        }
    }
}
