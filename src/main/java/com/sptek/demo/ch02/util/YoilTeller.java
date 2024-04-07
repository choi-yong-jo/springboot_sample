package com.sptek.demo.ch02.util;

import com.sptek.demo.ch02.model.YoilDate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Controller
public class YoilTeller {
    @RequestMapping("/getYoil")
    // http://localhost:8081/getYoil?year=2021&month=10&day=1
    public ModelAndView main(@ModelAttribute("yoilDate") YoilDate date, Model model) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("yoilError");

        if (!isValid(date)) {
            return mv;
        }

        // 2. 처리
        char yoil = getYoil(date);

        // @ModelAttribute를 사용하면 하단의 것은 필요없음.
//        mv.addObject("year", date.getYear());
//        mv.addObject("month", date.getMonth());
//        mv.addObject("day", date.getDay());
//        mv.addObject("yoil", yoil);

        mv.setViewName("yoil");

        return mv;

    }

    @ModelAttribute("yoil")
    private char getYoil(YoilDate date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();  // 모든 필드(날짜, 시간 등)을 초기화
        cal.set(date.getYear(), date.getMonth() - 1, date.getDay());

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        char yoil = "일월화수목금토".charAt(dayOfWeek-1); // dayofWeek는 일요일:1, 월요일:2, ...
        return yoil;
    }

    private boolean isValid(YoilDate date) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        if (date.getMonth() < 1 || date.getMonth() > 12) {
            return false;
        } else if (date.getDay() < 1 || date.getDay() > 31) {
            return false;
        } else if ((date.getMonth() == 2 || date.getMonth() == 4
                || date.getMonth() == 6 || date.getMonth() == 9
                || date.getMonth() == 11)
                && date.getDay() > 30) {
            return false;
        } else if (date.getMonth() == 2
                && date.getYear()%4 != 0
                && date.getDay() > 28) {
            return false;
        }

        return true;
    }
}
