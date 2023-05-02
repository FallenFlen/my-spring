package com.flz.myspring.ioc.beans.basic;

import com.flz.myspring.ioc.beans.exception.BeansException;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {
    void preInstantiateSingletons() throws BeansException;
}
