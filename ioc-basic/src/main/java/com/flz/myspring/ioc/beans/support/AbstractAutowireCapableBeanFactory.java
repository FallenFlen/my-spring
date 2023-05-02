package com.flz.myspring.ioc.beans.support;

import com.flz.myspring.ioc.beans.basic.AutowireCapableBeanFactory;
import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.basic.BeanReference;
import com.flz.myspring.ioc.beans.basic.PropertyValues;
import com.flz.myspring.ioc.beans.config.BeanPostProcessor;
import com.flz.myspring.ioc.beans.exception.BeansException;
import com.flz.myspring.ioc.beans.support.strategy.InstantiationStrategy;
import com.flz.myspring.ioc.beans.support.strategy.SimpleInstantiationStrategy;
import com.flz.myspring.ioc.beans.utils.BeanUtils;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        try {
            // 反射创建对象
            Object bean = instantiationStrategy.instantiate(beanDefinition, beanName, args);
            // 填充属性
            applyProperties(beanName, bean, beanDefinition);
            // 保存单例对象至DefaultSingletonBeanRegistry
            registrySingletonBean(beanName, bean);
            // 初始化bean
            initializeBean(beanName, bean, beanDefinition);
            return bean;
        } catch (Exception e) {
            throw new BeansException("create bean failed", e);
        }
    }

    private void initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 在执行init方法前执行BeanPostProcessor
        applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        // todo 实现自定义init方法
        invokeInitMethod();
        // 在执行init方法后执行BeanPostProcessor
        applyBeanPostProcessorsAfterInitialization(bean, beanName);
    }

    private void invokeInitMethod() {

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        // 同一个bean，经过每一个BeanPostProcessor时，会返回最新处理后的bean
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    private void applyProperties(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            propertyValues.getPropertyValues().forEach((pv) -> {
                String name = pv.getName();
                Object value = pv.getValue();
                // 如果成员变量是引用类型的，则需要先创建引用类型的bean
                // ex:UserService中有UserDao，所以要先创建UserDao的bean
                if (value instanceof BeanReference) {
                    value = getBean(((BeanReference) value).getBeanName());
                }

                BeanUtils.setField(bean, name, value);
            });
        } catch (Exception e) {
            throw new BeansException(String.format("apply properties for bean [%s] failed", beanName), e);
        }
    }

}
