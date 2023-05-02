package com.flz.myspring.beans.factory.context;

import com.flz.myspring.core.io.DefaultResourceLoader;
import com.flz.myspring.ioc.beans.basic.ConfigurableListableBeanFactory;
import com.flz.myspring.ioc.beans.config.BeanFactoryPostProcessor;
import com.flz.myspring.ioc.beans.config.BeanPostProcessor;
import com.flz.myspring.ioc.beans.exception.BeansException;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 创建bean factory
        refreshBeanFactory();

        // 获取bean factory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 在 Bean 实例化之前，执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // 在 Bean 实例化之前，注册BeanPostProcessor
        registerBeanPostProcessor(beanFactory);

        // 实例化单例bean
        beanFactory.preInstantiateSingletons();
    }

    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> processorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        processorMap.values().forEach(beanFactory::addBeanPostProcessor);
    }

    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> processorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        processorMap.values().forEach(processor -> processor.postProcessBeanFactory(beanFactory));
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory() throws BeansException;

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
}
