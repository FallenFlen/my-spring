package com.flz.myspring.iocxml.test;

import com.flz.myspring.core.support.XmlBeanDefinitionReader;
import com.flz.myspring.ioc.beans.support.DefaultListableBeanFactory;
import com.flz.myspring.iocxml.test.bean.MyService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IocParserTest {


    @Test
    void should_parse_xml_and_registry_bean_definition_successfully() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:myspring.xml");

        Object myServiceObj = beanFactory.getBean("myService");

        assertNotNull(myServiceObj);
        assertTrue(myServiceObj instanceof MyService);
        MyService myService = (MyService) myServiceObj;
        assertEquals("test-xml", myService.getName());
        assertEquals("user3", myService.getUsernameById("10002"));
    }
}
