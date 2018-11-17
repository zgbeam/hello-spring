package com.smile.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class MainConfigOfProfileTest {
    //1、使用命令行动态参数: 在虚拟机参数位置加载 -Dspring.profiles.active=test
    //2、代码的方式激活某种环境；
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().setActiveProfiles("dev");
        applicationContext.register(MainConfigOfProfile.class);
        applicationContext.refresh();

        String[] beanNames = applicationContext.getBeanNamesForType(DataSource.class);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}