package com.example.annotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 普通pojo类通过注解@Configuration，就可以成为spring的配置类
 */
@Configuration
public class AnnotationsBean {

    /**
     * Scope是配置bean的生命周期，普通只有singleton单例，prototype两种
     * 只有web项目才有多种
     * @return
     */
    @Bean(name = "BenzCar")
    @Scope("singleton")
    public Car bulidCar(){
        Car car=new Car();
        car.setName("奔驰");
        car.setMaxSpeed(250);
        return car;
    }
}
