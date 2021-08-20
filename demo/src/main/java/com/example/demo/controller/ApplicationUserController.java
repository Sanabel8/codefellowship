package com.example.demo.controller;

import com.example.demo.entity.ApplicationUser;
import com.example.demo.Repository.ApplicationUserRepository;
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

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup.html";
    }

    @GetMapping("/login")
    public String getSignInPage() {
        return "signin.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "firstName") String firstName,
                               @RequestParam(value = "lastName") String lastName,
                               @RequestParam(value = "password") String dateOfBirth,
                               @RequestParam(value = "bio") String bio) {
        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/signup");
    }

    @GetMapping("/users/{id}")
    public String getUserProfile(Principal principal,Model model, @PathVariable Integer id) {
        ApplicationUser user = applicationUserRepository.findById(id).get();
        model.addAttribute("requireduser",user);
        return "profile.html";
    }

    @GetMapping("/profile")
    public String getProfilePage(Principal p, Model m) {
        ApplicationUser requiredProfile = applicationUserRepository.findByUsername(p.getName());
        if (requiredProfile != null) {
            m.addAttribute("requireduser", requiredProfile);
            String requiredProfileUserName = requiredProfile.getUsername();
            String loggedInUserName = p.getName();
            boolean isLoggedInUserPofile = requiredProfileUserName.equals(loggedInUserName);
            m.addAttribute("isLoggedInUserPofile", isLoggedInUserPofile);
        } else {
            System.out.println("error messege");
        }
        return "profile.html";
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Principal p,Model model){
            model.addAttribute("userData",p.getName());
            model.addAttribute("Allusers",applicationUserRepository.findAll());
            ApplicationUser me = applicationUserRepository.findByUsername(p.getName());
            model.addAttribute("myFllowing",me.getFollowers());
        return "allUsers.html";
    }

    @PostMapping("/follow/{id}")
    public RedirectView addFollow(@PathVariable("id") int id , Principal principal){
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        ApplicationUser followed = applicationUserRepository.findById(id).get();
        user.addNewFollowed(followed);
        applicationUserRepository.save(user);
        return new RedirectView("/allUsers");
    }

    @GetMapping("/feed")
    public String getFollowingInfo(Principal p, Model model){
        ApplicationUser myPage = applicationUserRepository.findByUsername(p.getName());
            model.addAttribute("Allfollowing",myPage.getFollowers());
        return "feed.html";
    }

}