package com.flz.myspring.ioc.beans.basic;

import com.flz.myspring.ioc.beans.exception.BeansException;
import org.apache.commons.lang3.StringUtils;

public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        if (StringUtils.isBlank(name)) {
            throw new BeansException("property name does not allow null or blank value");
        }
        this.name = name.trim();
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
