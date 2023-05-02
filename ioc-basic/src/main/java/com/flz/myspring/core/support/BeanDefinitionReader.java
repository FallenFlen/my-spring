package com.flz.myspring.core.support;

import com.flz.myspring.core.io.Resource;
import com.flz.myspring.core.io.ResourceLoader;
import com.flz.myspring.ioc.beans.basic.BeanDefinitionRegistry;
import com.flz.myspring.ioc.beans.exception.BeansException;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
