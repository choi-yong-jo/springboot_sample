package com.sptek.demo.ch03.di02;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

class Car {

    @Autowired      // byType
    Engine engine;

    @Resource       // byName
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}

class SportCar extends Car{
    @Override
    public String toString() {
        return "SportCar{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

class Engine {}

class Door {}

public class Main {
    public static void main(String[] args) {
        // AppContext(Class clazz) - 설정파일 자바클래스 지정
        AppContext ac = new AppContext(AppConfig.class);
        Car car = (Car) ac.getBean("car");
        Car car2 = (Car) ac.getBean(Car.class);
        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean(Door.class);

        // 빈들끼리의 관계를 설정 - 수동
//        car.setEngine(engine);
//        car.setDoor(door);

        System.out.println("car = " + car);
        System.out.println("car2 = " + car2);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);
    }
}
