package com.flz.myspring.ioc.beans.basic;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private final List<PropertyValue> propertyValues = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValues() {
        return new ArrayList<>(this.propertyValues);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        return this.propertyValues.stream()
                .filter(propertyValue -> StringUtils.equals(propertyValue.getName(), propertyName))
                .findFirst()
                .orElse(null);
    }
}
