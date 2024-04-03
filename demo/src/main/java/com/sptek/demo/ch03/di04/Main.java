package com.sptek.demo.ch03.di04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.util.Arrays;

@Component
class Car {
    public String toString() {
        return "Car{}";
    }
}

@Component
class Bus extends Car{
    public String toString() {
        return "Bus{}";
    }
}

@Component
class Truck extends Car{
    public String toString() {
        return "Truck{}";
    }
}

// @Import : [ApplicationContext case 2]
//@Import({Config1.class, Config2.class})
//@Import(MyImportSelector.class)
@EnableMyAutoConfiguration("test2")
class MainConfig {
    @Bean
    Car car() {
        return new Car();
    }
}

class Config1 {
    @Bean
    Car Bus() {
        return new Bus();
    }
}

class Config2 {
    @Bean
    Car Truck() {
        return new Truck();
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportSelector.class)
@interface EnableMyAutoConfiguration {
    String value() default "";
}

class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attr = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableMyAutoConfiguration.class.getName(), false));
        String mode = attr.getString("value");
//        String mode = "test";
        if(mode.equals("test")) {
            return new String[]{Config1.class.getName()};
        } else {
            return new String[]{Config2.class.getName()};
        }
    }
}

@Component
@Conditional(TrueCondition.class)
class Engine {
    public String toString() {
        return "Engine{}";
    }
}

@Component
@Conditional(OsCondition.class)
class Door {
    public String toString() {
        return "Door{}";
    }
}

class TrueCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}

class OsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        System.out.println("System.getProperties() = " + System.getProperties());
        return env.getProperty("mode").equals("test");
    }
}

@EnableConfigurationProperties({Myproperties.class})
@SpringBootApplication
//@SpringBootConfiguration
//@ComponentScan
public class Main implements CommandLineRunner {

    @Autowired
    Myproperties prop;      // 인스턴스 변수, 자동 주입

    @Autowired
    ApplicationContext ac;

    @Override
    public void run(String... args) throws Exception {      // 인스턴스 메서드
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        Arrays.sort(beanDefinitionNames);            // 빈 목록이 담긴 배열을 정렬
        Arrays.stream(beanDefinitionNames)           // 배열을 스트림으로 변환
                .filter(b->!b.startsWith("org"))        // org로 시작하는 빈의 이름을 제외
                .forEach(System.out::println);       // 스트림의 요소를 하나씩 꺼내서 출력

        System.out.println("prop.getDomain() = " + prop.getDomain());
        System.out.println("prop.getEmail() = " + prop.getEmail());
    }

    public static void main(String[] args) {    // static 메서드
        ApplicationContext ac = SpringApplication.run(Main.class, args);
//        System.out.println("ac = " + ac);
        // [ApplicationContext case 1]
//        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class, Config1.class, Config2.class);
        // [ApplicationContext case 2]


//        System.out.println("ac.getBean(\"Bus\") = " + ac.getBean("Bus"));

//        Myproperties prop = ac.getBean(Myproperties.class);       // 수동으로 주입
//        System.out.println("prop.getDomain() = " + prop.getDomain());
//        System.out.println("prop.getEmail() = " + prop.getEmail());
    }

    @Bean
    MyBean myBean() {return new MyBean();}

}

class MyBean {}

