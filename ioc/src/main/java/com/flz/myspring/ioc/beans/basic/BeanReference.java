package com.flz.myspring.ioc.beans.basic;

/**
 * bean引用，保存引用类型的类名，用于含有引用类型成员变量的bean的初始化
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
