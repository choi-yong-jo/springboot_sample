package com.sptek.demo.ch03.di01;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Data
class Car {

    @Autowired
    Engine engine;
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

class SportCar extends Car {}

class Truck extends Car {}

class Engine {}

class Door {}

public class Main {
    public static void main(String[] args) throws Exception {
        Car car = (Car) getObject("car");
        Engine engine = (Engine) getObject("engine");
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
    }
    
    static Object getObject(String key) throws Exception{
        Properties prop = new Properties();
        Class clazz = null;
        try {
            prop.load(new FileReader("ch03_model.txt"));
            String className = prop.getProperty(key);       // 지정한 key의 value를 반환
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        if(key.equals("car")){
//            return new SportCar();
//        } else if (key.equals("engine")){
//            return new Engine();
//        } else if (key.equals("engine")) {
//            return new Door();
//        }
        return clazz.newInstance();
    }

    static Car getCar() {
        return new SportCar();
    }
}
