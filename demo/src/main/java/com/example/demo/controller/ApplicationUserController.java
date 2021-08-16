package com.example.demo.controller;

import com.example.demo.entity.ApplicationUser;
import com.example.demo.entity.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.xml.crypto.Data;
import java.security.Principal;

public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/")
    public String getHomePage(Model model, Principal principal){
        model.addAttribute("userData",principal.getName());
        return "index.html";
    }
    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup.html";
    }

    @GetMapping("/login")
    public String getSignInPage(){
        return "signin.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam(value="username") String username,
                               @RequestParam(value="password") String password,
                               @RequestParam(value="firstName") String firstName,
                               @RequestParam(value="lastName") String lastName,
                               @RequestParam(value="password") Data dateOfBirth,
                               @RequestParam(value="bio") String bio ){
        ApplicationUser newUser = new ApplicationUser(username,bCryptPasswordEncoder.encode(password),firstName,lastName,dateOfBirth,bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/login");
    }
    @GetMapping("/user/{id}")
    public String getUserPage(@PathVariable("id") Integer id, Model model ){
    model.addAttribute("userPage",applicationUserRepository.findById(id).get());
        return"user.html";
    }
    @GetMapping(value = "/user")
    public String getProfile(Principal currentUser , Model user) {
        user.addAttribute("user" , applicationUserRepository.findByUsername(currentUser.getName()));
        user.addAttribute("username" , currentUser.getName());
        return "user";
    }
}
