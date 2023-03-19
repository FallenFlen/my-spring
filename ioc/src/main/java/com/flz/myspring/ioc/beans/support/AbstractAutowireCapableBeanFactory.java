package com.flz.myspring.ioc.beans.support;

import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.exception.BeansException;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            // 反射创建对象
            bean = beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeansException("create bean failed:" + e);
        }
        // 保存单例对象至DefaultSingletonBeanRegistry
        registrySingletonBean(beanName, bean);
        return bean;
    }
}
