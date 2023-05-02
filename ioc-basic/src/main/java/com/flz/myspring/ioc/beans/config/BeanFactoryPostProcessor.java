package com.flz.myspring.ioc.beans.config;

import com.flz.myspring.ioc.beans.basic.ConfigurableListableBeanFactory;
import com.flz.myspring.ioc.beans.exception.BeansException;

/**
 * bean factory后置处理器，钩子
 * 执行时机：bean创建之前
 * 可以用它来修改跟bean定义等相关的信息
 */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
