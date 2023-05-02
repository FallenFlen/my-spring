package com.flz.myspring.ioc.beans.basic;

import com.flz.myspring.ioc.beans.config.BeanPostProcessor;

// todo
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
