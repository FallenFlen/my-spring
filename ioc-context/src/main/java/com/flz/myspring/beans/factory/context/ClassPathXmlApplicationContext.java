package com.flz.myspring.beans.factory.context;

import com.flz.myspring.ioc.beans.exception.BeansException;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    // 配置文件路径
    private String[] locations;

    public ClassPathXmlApplicationContext(String location) {
        this(new String[]{location});
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     *
     * @param locations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String... locations) {
        this.locations = locations;
        refresh();
    }


    @Override
    protected String[] getConfigLocations() {
        return this.locations;
    }
}
