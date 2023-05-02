package com.flz.myspring.factory.context.test;

import com.flz.myspring.beans.factory.context.ClassPathXmlApplicationContext;
import com.flz.myspring.factory.context.test.bean.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContextTest {
    @Test
    void should_create_bean_with_bean_post_processors_and_bean_factory_post_processors_executing() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:myspring-context-with-pp.xml");
        UserService userService = context.getBean("userService", UserService.class);
        Assertions.assertEquals("context-2号", userService.queryUserInfo("1002"));
        Assertions.assertEquals("Valve update to Valve-2.0", userService.getCompany());
        Assertions.assertEquals("location update to Chengdu", userService.getLocation());
    }

    @Test
    void should_create_bean_without_any_bean_post_processors_and_bean_factory_post_processors_executing() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:myspring-context-without-pp.xml");
        UserService userService = context.getBean("userService", UserService.class);
        Assertions.assertEquals("context-3号", userService.queryUserInfo("1003"));
        Assertions.assertEquals("Valve", userService.getCompany());
        Assertions.assertEquals("America", userService.getLocation());
    }
}
