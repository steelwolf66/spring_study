package com.zhouyu.service;

import com.zhouyu.spring.*;

@Scope("singleton")
@Component("userService")
public class UserService implements UserInterface, BeanNameAware , InitializingBean {
    @Autowired
    private OrderService orderService;

    private String beanName;

    private String age;

    @Override
    public void test(){
        System.out.println(orderService);
    }

    @Override
    public void setBaenName(String beanName) {
        this.beanName = beanName;
    }

    public void initAge(){
        this.age = "18";
    }

    @Override
    public void afterPropertiesSet() {
        initAge();
    }
}
