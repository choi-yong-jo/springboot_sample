package com.sptek.demo.ch02.controller;

import com.sptek.demo.ch02.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

// 1. 원격 프로그램으로 등록
@Controller
public class HomeController {
    // 2. URL과 메서드를 연결
    @RequestMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping(value="/test")
    public String test(Model model, HttpServletRequest req) {
        req.setAttribute("year", 2024);

        HttpSession session = req.getSession(); //요창으로부터 session 객체 저장
        session.setAttribute("id", "cyjo1207");

        ServletContext application = session.getServletContext();
        application.setAttribute("email", "cyjo1207@gmail.com");

        model.addAttribute("lastName", "Choi");
        model.addAttribute("firstName", "YongJo");
        model.addAttribute("list", Arrays.asList("aaa","bbb","ccc","ddd","eee"));
        model.addAttribute("bno", 1);
        model.addAttribute("user", new User("aaa", 30));
        return "test";
    }

}
