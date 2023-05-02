package com.flz.myspring.beans.factory.context;

import com.flz.myspring.ioc.beans.basic.ConfigurableListableBeanFactory;
import com.flz.myspring.ioc.beans.exception.BeansException;
import com.flz.myspring.ioc.beans.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        this.beanFactory = beanFactory;
        loadBeanDefinitions(beanFactory);
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() throws BeansException {
        return this.beanFactory;
    }
}
