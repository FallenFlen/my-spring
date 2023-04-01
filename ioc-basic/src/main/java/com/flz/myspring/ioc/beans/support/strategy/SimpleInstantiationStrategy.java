package com.flz.myspring.ioc.beans.support.strategy;

import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.exception.BeansException;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 反射创建对象策略：
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            Constructor targetConstructor = getTargetConstructor(beanClass, args);
            return ArrayUtils.isEmpty(args) ? targetConstructor.newInstance() : targetConstructor.newInstance(args);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException |
                 IllegalAccessException e) {
            throw new BeansException(String.format("failed to instantiate [%s]", beanClass.getName()), e);
        }
    }

    private Constructor getTargetConstructor(Class beanClass, Object[] args) throws NoSuchMethodException {
        return Arrays.stream(beanClass.getDeclaredConstructors())
                .filter(constructor -> ArrayUtils.isNotEmpty(args) && constructor.getParameterTypes().length == args.length)
                .findFirst()
                .orElse(beanClass.getDeclaredConstructor());
    }
}
