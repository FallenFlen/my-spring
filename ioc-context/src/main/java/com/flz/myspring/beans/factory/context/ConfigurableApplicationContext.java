package com.flz.myspring.beans.factory.context;

import com.flz.myspring.ioc.beans.exception.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
