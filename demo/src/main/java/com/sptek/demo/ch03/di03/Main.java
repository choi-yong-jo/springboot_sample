package com.sptek.demo.ch03.di03;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Map;

@Component
class Car {

    // 1. Resource
//    @Resource(name = "superEngine")
    // 2. Autowired @Qualifier
    @Autowired
    @Qualifier("turdoEngine")
    // 3. @Inject - @Autowired와 같음 required=false가 없음
//    @Inject
    Engine engine;

    @Autowired
    Door door;

    public Car() {}

//    @Autowired
//    public Car(Engine[] engine, Door door) {
//        this.engine = engine;
//        this.door = door;
//    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

class Engine {}

@Component
class SuperEngine extends Engine {}

@Component
class TurdoEngine extends Engine {}

@Component
class Door {}

public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Car car = ac.getBean("car", Car.class);      // byName 객체을 조회
        System.out.println("car = " + car);

//        Engine engine = ac.getBean(Engine.class);
//        Engine engine2 = ac.getBean(Engine.class);
//        Engine engine3 = ac.getBean(Engine.class);
//
//        System.out.println("car = " + car);
//        System.out.println("engine = " + engine);
//        System.out.println("engine2 = " + engine2);
//        System.out.println("engine3 = " + engine3);

        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount());
        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames()));
//        System.out.println("ac.containsBeanDefinition(\"engine\") = " + ac.containsBeanDefinition("engine"));
//        System.out.println("ac.isSingleton(\"car\") = " + ac.isSingleton("car"));
//        System.out.println("ac.isPrototype(\"car\") = " + ac.isPrototype("car"));
//        System.out.println("ac.isPrototype(\"engine\") = " + ac.isPrototype("engine"));

        SysInfo info = ac.getBean(SysInfo.class);
        System.out.println("info = " + info);

        Map<String, String> env = System.getenv();
        System.out.println("env = " + env);

    }
}
