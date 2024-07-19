package com.example.locationproject.controllers;

import com.example.locationproject.entities.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("")
    public String viewHomePage(){

        return "index";
    }


    @GetMapping("/login")
    public String viewLoginPage(){
//        model.addAttribute("admin", new Admin());
        return "login";
    }


}
