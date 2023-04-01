package com.flz.myspring.ioc.beans.utils;

import com.flz.myspring.ioc.beans.exception.BeansException;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

public class BeanUtils {
    public static void setField(Object bean, String fieldName, Object value) {
        Objects.requireNonNull(bean);
        try {
            Field declaredField = bean.getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            declaredField.set(bean, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new BeansException("set field for bean failed", e);
        }

    }
}
