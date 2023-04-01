package com.flz.myspring.ioc.beans.support;

import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.basic.BeanFactory;
import com.flz.myspring.ioc.beans.exception.BeansException;

import java.util.Optional;

/**
 * 抽象单例bean工厂，组合bean注册器拥有了如下功能
 * - 创建单例bean
 * - 将创建和的单例bean进行注册
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    /**
     * 获取bean
     * - 如果bean存在于容器，则直接返回
     * - 如果不存在，则使用BeanDefinition创建后返回
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) {
        return getBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return Optional.ofNullable(getSingletonBean(beanName))
                .orElseGet(() -> {
                    BeanDefinition beanDefinition = getBeanDefinition(beanName);
                    return createBean(beanName, beanDefinition, args);
                });
    }

    /**
     * 获取bean定义，由子类实现
     *
     * @param beanName
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    /**
     * 根据bean定义和bean名称创建bean，由子类实现
     *
     * @param beanName
     * @param beanDefinition
     * @return
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;
}
