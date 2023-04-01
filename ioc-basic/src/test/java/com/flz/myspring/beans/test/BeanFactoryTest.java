package com.flz.myspring.beans.test;

import com.flz.myspring.beans.test.bean.MyDao;
import com.flz.myspring.beans.test.bean.MyService;
import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.basic.BeanFactory;
import com.flz.myspring.ioc.beans.basic.BeanReference;
import com.flz.myspring.ioc.beans.basic.PropertyValue;
import com.flz.myspring.ioc.beans.basic.PropertyValues;
import com.flz.myspring.ioc.beans.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BeanFactoryTest {

    @Test
    void should_create_obj_get_bean_and_apply_properties_successfully() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registryBeanDefinition("myDao", new BeanDefinition(MyDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "abcd"));
        propertyValues.addPropertyValue(new PropertyValue("myDao", new BeanReference("myDao")));
        beanFactory.registryBeanDefinition("myService", new BeanDefinition(MyService.class, propertyValues));

        Object myService = beanFactory.getBean("myService", "test");

        assertNotNull(myService);
        assertTrue(myService instanceof MyService);
        assertEquals("abcd", ((MyService) myService).getName());
        assertEquals("user1", ((MyService) myService).getUsernameById("10000"));
    }

    @Test
    void should_create_obj_with_args_constructor_and_get_bean_successfully() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registryBeanDefinition("myService", new BeanDefinition(MyService.class));
        Object myService = beanFactory.getBean("myService", "test");

        assertNotNull(myService);
        assertTrue(myService instanceof MyService);
        assertEquals("test", ((MyService) myService).getName());
    }

    @Test
    void should_create_obj_with_no_args_constructor_and_get_bean_successfully() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registryBeanDefinition("myService", new BeanDefinition(MyService.class));
        Object myService = beanFactory.getBean("myService");

        assertNotNull(myService);
        assertTrue(myService instanceof MyService);
    }
}
