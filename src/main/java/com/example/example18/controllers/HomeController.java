package com.example.example18.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value ={"","/","/home"})
    public ModelAndView displayHome(ModelAndView modelAndView) {
//        model.addAttribute("username","Syed");
//        return "home.html";
//        modelAndView.addObject("username","Usman");
        modelAndView.setViewName("home.html");
        return modelAndView;
    }
}
