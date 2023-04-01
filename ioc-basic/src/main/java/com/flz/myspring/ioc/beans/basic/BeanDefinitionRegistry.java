package com.flz.myspring.ioc.beans.basic;

/**
 * bean定义的注册器：用于注册(保存)BeanDefinition
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册bean定义
     *
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
