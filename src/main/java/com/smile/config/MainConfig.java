package com.smile.config;

import com.smile.bean.ColorFactoryBean;
import com.smile.bean.Person;
import com.smile.bean.Woman;
import com.smile.config.conditional.LinuxCondition;
import com.smile.config.conditional.WindowCondition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

// 告诉Spring这是一个配置类，配置类=配置文件
@Configuration

//@ComponentScan  value:指定要扫描的包
//excludeFilters = Filter[] ：指定扫描的时候按照什么规则排除那些组件
//includeFilters = Filter[] ：指定扫描的时候只需要包含哪些组件
//FilterType.ANNOTATION：按照注解
//FilterType.ASSIGNABLE_TYPE：按照给定的类型；
//FilterType.ASPECTJ：使用ASPECTJ表达式
//FilterType.REGEX：使用正则指定
//FilterType.CUSTOM：使用自定义规则
@ComponentScan(value = "com.smile", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)}, useDefaultFilters = false)
@ComponentScan(value = "com.smile", includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, value = MyTypeFilter.class)}, useDefaultFilters = false)

@Import({Woman.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})

//类中组件统一设置，满足当前条件，这个类中配置的所有bean注册才能生效
@Conditional({WindowCondition.class})
public class MainConfig {

    /**
     * 默认是单实例的
     * ConfigurableBeanFactory#SCOPE_PROTOTYPE
     * ConfigurableBeanFactory#SCOPE_SINGLETON
     * org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST  request
     * org.springframework.web.context.WebApplicationContext#SCOPE_SESSION	session
     * <p>
     * prototype：多实例的：ioc容器启动并不会去调用方法创建对象放在容器中。
     * 每次获取的时候才会调用方法创建对象；
     * singleton：单实例的（默认值）：ioc容器启动会调用方法创建对象放到ioc容器中。
     * 以后每次获取就是直接从容器（map.get()）中拿，
     * request：同一次请求创建一个实例
     * session：同一个session创建一个实例
     * <p>
     * 懒加载：
     * 单实例bean：默认在容器启动的时候创建对象；
     * 懒加载：容器启动不创建对象。第一次使用(获取)Bean创建对象，并初始化；
     */
    @Scope("prototype")

    // 给容器中注册一个Bean;类型为返回值的类型，id默认是用方法名作为id
    @Bean("person")
    public Person person() {
        return new Person();
    }


    @Bean
    public Person bill() {
        return new Person("bill");
    }

    @Conditional({LinuxCondition.class})
    @Bean
    public Person linus() {
        return new Person("linus");
    }

    /**
     * 给容器中注册组件；
     * 1）、包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
     * 2）、@Bean[导入的第三方包里面的组件]
     * 3）、@Import[快速给容器中导入一个组件]
     * 		1）、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
     * 		2）、ImportSelector:返回需要导入的组件的全类名数组；
     * 		3）、ImportBeanDefinitionRegistrar:手动注册bean到容器中
     * 4）、使用Spring提供的 FactoryBean（工厂Bean）;
     * 		1）、默认获取到的是工厂bean调用getObject创建的对象
     * 		2）、要获取工厂Bean本身，我们需要给id前面加一个&
     * 			&colorFactoryBean
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
