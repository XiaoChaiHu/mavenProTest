package com.example.annotations;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationsTest {

    @Test
    public void annotationsBeanTest(){
        ApplicationContext applicationContext= new AnnotationConfigApplicationContext(AnnotationsBean.class);
        Car benzCar = applicationContext.getBean("BenzCar", Car.class);
        System.out.println("annotationsBeanTest::"+benzCar.toString());
    }
}
