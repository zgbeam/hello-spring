package com.smile.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class MainConfigOfPropertyValueTest {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValue.class);
        System.out.println(applicationContext.getBean("person"));

        Environment environment = applicationContext.getEnvironment();
        System.out.println(environment.getProperty("person.nickName"));

    }
}