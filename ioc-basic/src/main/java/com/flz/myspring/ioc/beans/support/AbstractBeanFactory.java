package com.flz.myspring.ioc.beans.support;

import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.basic.ConfigurableBeanFactory;
import com.flz.myspring.ioc.beans.config.BeanPostProcessor;
import com.flz.myspring.ioc.beans.exception.BeansException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 抽象单例bean工厂，组合bean注册器拥有了如下功能
 * - 创建单例bean
 * - 将创建和的单例bean进行注册
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

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
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    protected <T> T doGetBean(String beanName, Object... args) {
        return (T) Optional.ofNullable(getSingletonBean(beanName))
                .orElseGet(() -> {
                    BeanDefinition beanDefinition = getBeanDefinition(beanName);
                    return createBean(beanName, beanDefinition, args);
                });
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
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
