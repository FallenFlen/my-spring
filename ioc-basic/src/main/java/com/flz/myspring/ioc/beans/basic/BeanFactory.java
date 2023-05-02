package com.flz.myspring.ioc.beans.basic;

import com.flz.myspring.ioc.beans.exception.BeansException;

/**
 * bean工厂接口，获取bean的核心接口
 */
public interface BeanFactory {
    Object getBean(String beanName);

    Object getBean(String beanName, Object... args);

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
