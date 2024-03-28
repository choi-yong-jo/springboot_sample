package com.sptek.demo.ch02;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String main() {
        System.out.println("Hello");
        return "Hello";
    }
}
