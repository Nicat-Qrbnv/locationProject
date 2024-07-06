package com.example.locationproject.controller;

import com.example.locationproject.dto.LoginRequest;
import com.example.locationproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

//    @GetMapping("/auth/login")
//    public String loginPage() {
//        return "redirect:/swagger-ui.html";
//    }
//
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, Model model) {
        if (loginService.login(loginRequest)) {

            return "redirect:/swagger-ui.html";
        } else {

            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }
}
