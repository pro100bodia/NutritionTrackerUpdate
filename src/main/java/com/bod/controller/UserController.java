package com.bod.controller;

import com.bod.converter.GenderConverter;
import com.bod.converter.LifeStyleConverter;
import com.bod.converter.RoleConverter;
import com.bod.domain.Gender;
import com.bod.domain.LifeStyle;
import com.bod.domain.Role;
import com.bod.domain.User;
import com.bod.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public String getContent(){
        return "user";
    }

    @GetMapping("/signin")
    public String showSignInPage(){return "signin";}

    @PostMapping("/signin")
    public String addUser(User user){
        User userFromDb = userRepository.findByName(user.getName());

        if(userFromDb != null){
            return "signin";
        }

        userRepository.save(user);

        return "redirect:/login";
    }

    @InitBinder
    public void initBinderForLifeStyle(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(LifeStyle.class, new LifeStyleConverter());
    }

    @InitBinder
    public void initBinderForRole(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(Role.class, new RoleConverter());
    }

    @InitBinder
    public void initBinderForGender(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(Gender.class, new GenderConverter());
    }
}
