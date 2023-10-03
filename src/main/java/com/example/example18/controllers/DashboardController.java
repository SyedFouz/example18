package com.example.example18.controllers;

import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {


    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String displayDashboard(Model model, Authentication authentication){
        model.addAttribute("username",authentication.getName());
        model.addAttribute("roles",authentication.getAuthorities().toString());
        //throw new RuntimeException("Phas gya na bedu");
        return "dashboard.html";
    }
}
