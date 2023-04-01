package com.flz.myspring.ioc.beans.support;

import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.basic.BeanReference;
import com.flz.myspring.ioc.beans.basic.PropertyValues;
import com.flz.myspring.ioc.beans.exception.BeansException;
import com.flz.myspring.ioc.beans.support.strategy.InstantiationStrategy;
import com.flz.myspring.ioc.beans.support.strategy.SimpleInstantiationStrategy;
import com.flz.myspring.ioc.beans.utils.BeanUtils;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
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
            return bean;
        } catch (Exception e) {
            throw new BeansException("create bean failed", e);
        }
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
