package com.flz.myspring.ioc.beans.support;

import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.exception.BeansException;
import com.flz.myspring.ioc.beans.support.strategy.InstantiationStrategy;
import com.flz.myspring.ioc.beans.support.strategy.SimpleInstantiationStrategy;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        try {
            // 反射创建对象
            Object bean = instantiationStrategy.instantiate(beanDefinition, beanName, args);
            // 保存单例对象至DefaultSingletonBeanRegistry
            registrySingletonBean(beanName, bean);
            return bean;
        } catch (Exception e) {
            throw new BeansException("create bean failed:" + e);
        }
    }

}
