package com.flz.myspring.factory.context.test.bean;

import com.flz.myspring.ioc.beans.config.BeanPostProcessor;
import com.flz.myspring.ioc.beans.exception.BeansException;

public class MyBPP implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("this is my BPP running");
        if ("userService".equals(beanName)) {
            System.out.println("this is my BPP running:execute developer custom logic");
            UserService userService = (UserService) bean;
            userService.setLocation("location update to Chengdu");
        }
        System.out.println("this is my BPP done");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
