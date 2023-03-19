package com.flz.myspring.ioc.beans.support;

import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.basic.BeanDefinitionRegistry;
import com.flz.myspring.ioc.beans.exception.BeansException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 核心实现类，实现BeanDefinition的注册
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 注册BeanDefinition，对外主要使用这个方法进行BeanDefinition真正的注册
     * 待注册完之后，可以使用BeanFactory::getBean方法获取真正的bean
     *
     * @param beanName
     * @param beanDefinition
     */
    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        return Optional.ofNullable(this.beanDefinitionMap.get(beanName))
                .orElseThrow(() -> new BeansException(String.format("no bean found with name '%s'", beanName)));
    }
}
