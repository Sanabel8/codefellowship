package com.example.demo.controller;

import com.example.demo.Repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String getHome(Model model, Principal principal){
        model.addAttribute("userData",principal.getName());
        return "home.html";
    }
}
