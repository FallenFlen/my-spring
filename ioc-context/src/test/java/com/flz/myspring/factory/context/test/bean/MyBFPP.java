package com.flz.myspring.factory.context.test.bean;

import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.basic.ConfigurableListableBeanFactory;
import com.flz.myspring.ioc.beans.basic.PropertyValue;
import com.flz.myspring.ioc.beans.config.BeanFactoryPostProcessor;
import com.flz.myspring.ioc.beans.exception.BeansException;

public class MyBFPP implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("this is my BFPP running");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue("company", "Valve update to Valve-2.0"));
        System.out.println("this is my BFPP done");
    }
}
