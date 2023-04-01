package com.flz.myspring.ioc.beans.basic;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PropertyValues {
    private final List<PropertyValue> propertyValues = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        validate(propertyValue);
        this.propertyValues.add(propertyValue);
    }

    public void addPropertyValues(List<PropertyValue> propertyValues) {
        if (CollectionUtils.isEmpty(propertyValues)) {
            return;
        }

        propertyValues.forEach(this::validate);
        this.propertyValues.addAll(propertyValues);
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

    private void validate(PropertyValue propertyValue) {
        Objects.requireNonNull(propertyValue);
    }

}
