package com.sptek.demo.ch03.di04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(Main.class, args);
        System.out.println("ac = " + ac);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        Arrays.sort(beanDefinitionNames);            // 빈 목록이 담긴 배열을 정렬
        Arrays.stream(beanDefinitionNames)           // 배열을 스트림으로 변환
                .filter(b->!b.startsWith("org"))        // org로 시작하는 빈의 이름을 제외
                .forEach(System.out::println);       // 스트림의 요소를 하나씩 꺼내서 출력
    }

    @Bean
    MyBean myBean() {return new MyBean();}
}

class MyBean {}

