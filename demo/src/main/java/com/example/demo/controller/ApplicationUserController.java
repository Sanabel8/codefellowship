package com.example.demo.controller;

import com.example.demo.entity.ApplicationUser;
import com.example.demo.entity.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String getSplashPage(Principal principal){
        return "index.html";
    }
    @GetMapping("/home")
    public String getHome(Model model, Principal principal){
        model.addAttribute("userData",principal.getName());
        return "home.html";
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
                               @RequestParam(value="password") String dateOfBirth,
                               @RequestParam(value="bio") String bio ){
        ApplicationUser newUser = new ApplicationUser(username,bCryptPasswordEncoder.encode(password),firstName,lastName,dateOfBirth,bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/signup");
    }

    @GetMapping("/user/{id}")
    public String getUserProfile(Principal principal, Model model, @PathVariable Integer id){
        ApplicationUser user = applicationUserRepository.findById(id).get();
        model.addAttribute("userData", user);
        model.addAttribute("userProfile", user.getUsername());

        return "user.html";
    }
    @GetMapping(value = "/profile")
    public String getProfile(Principal principal , Model model) {
        model.addAttribute("userData" , principal.getName());
        model.addAttribute("userProfile" , applicationUserRepository.findByUsername(principal.getName()));

        return "profile.html";
    }
    @GetMapping("/error")
    public  String errorPart(){
        return "error.html";
    }

}
