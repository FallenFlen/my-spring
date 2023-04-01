package com.flz.myspring.ioc.beans.support.strategy;

import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.exception.BeansException;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException;
}
