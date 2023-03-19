package com.flz.myspring.ioc.beans.support;

import com.flz.myspring.ioc.beans.basic.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例bean注册
 */
public abstract class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String, Object> beanMap = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) {
        return beanMap.get(beanName);
    }

    protected void addSingletonBean(String beanName, Object singletonBean) {
        this.beanMap.put(beanName, singletonBean);
    }
}
