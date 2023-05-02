package com.flz.myspring.beans.factory.context;

import com.flz.myspring.ioc.beans.exception.BeansException;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private String[] locations;

    public ClassPathXmlApplicationContext(String location) {
        this(new String[]{location});
    }

    public ClassPathXmlApplicationContext(String... locations) {
        this.locations = locations;
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return null;
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return null;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    protected String[] getConfigLocations() {
        return this.locations;
    }
}
