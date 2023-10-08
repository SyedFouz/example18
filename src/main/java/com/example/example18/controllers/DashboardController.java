package com.example.example18.controllers;

import com.example.example18.model.Person;
import com.example.example18.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        Person person=personRepository.readByName(authentication.getName());
        if (null!=person.getEazyClass() && null!=person.getEazyClass().getName()){
            model.addAttribute("enrolledClass",person.getEazyClass().getName());
        }
        session.setAttribute("loggedInPerson",person);

        return "dashboard.html";
    }
}
