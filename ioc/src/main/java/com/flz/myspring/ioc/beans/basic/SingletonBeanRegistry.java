package com.flz.myspring.ioc.beans.basic;

/**
 * 单例bean注册接口
 */
public interface SingletonBeanRegistry {
    Object getSingletonBean(String beanName);

    void registrySingletonBean(String beanName, Object singletonBean);
}
