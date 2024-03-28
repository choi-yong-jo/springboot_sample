package com.sptek.demo.ch02;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Controller
public class YoilTeller {
    @RequestMapping("/yoil") // http://localhost:8080/getYoil?year=2021&month=10&day=1
    //    public static void main(String[] args) {
    public ModelAndView main(int year, int month, int day, Model model) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("yoilError");

        if (!isValid(year, month, day)) {
            return mv;
        }

        // 2. 처리
        Calendar cal = Calendar.getInstance();
        cal.clear();  // 모든 필드(날짜, 시간 등)을 초기화
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        char yoil = "일월화수목금토".charAt(dayOfWeek-1); // dayofWeek는 일요일:1, 월요일:2, ...

        mv.addObject("year", year);
        mv.addObject("month", month);
        mv.addObject("day", day);
        mv.addObject("yoil", yoil);
        mv.setViewName("yoil");

        return mv;

    }

    private boolean isValid(int year, int month , int day) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        if (month < 1 || month > 12) {
            return false;
        } else if (day < 1 || day > 31) {
            return false;
        } else if ((month == 2 || month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        } else if (month == 2 && year%4 != 0 && day > 28) {
            return false;
        }

        return true;
    }
}
