package com.flz.myspring.ioc.beans.config;

import com.flz.myspring.ioc.beans.basic.ConfigurableListableBeanFactory;
import com.flz.myspring.ioc.beans.exception.BeansException;

public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
