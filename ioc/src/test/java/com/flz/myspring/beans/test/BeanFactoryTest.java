package com.flz.myspring.beans.test;

import com.flz.myspring.beans.test.bean.MyService;
import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.basic.BeanFactory;
import com.flz.myspring.ioc.beans.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BeanFactoryTest {

    @Test
    void should_create_and_get_bean_successfully() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registryBeanDefinition("myService", new BeanDefinition(MyService.class));
        Object myService = beanFactory.getBean("myService");

        assertNotNull(myService);
        assertTrue(myService instanceof MyService);
    }
}
