package com.flz.myspring.ioc.beans.basic;

/**
 * bean工厂接口，获取bean的核心接口
 */
public interface BeanFactory {
    Object getBean(String beanName);

    Object getBean(String beanName, Object... args);
}
