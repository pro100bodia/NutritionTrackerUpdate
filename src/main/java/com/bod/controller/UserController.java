package com.bod.controller;

import com.bod.domain.Food;
import com.bod.domain.User;
import com.bod.repository.FoodRepository;
import com.bod.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodRepository foodRepository;

    @GetMapping("/user")
    public String getContent(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userRepository.findByName(username);

        model.addAttribute("user", user);

        List<Food> foodItems = foodRepository.findAll();
        for(Food foodItem : foodItems){
            LOG.info(foodItem);
        }
        model.addAttribute("foodItems", foodItems);
        return "user";
    }

    @GetMapping("/signin")
    public String showSignInPage(){return "signin";}

    @PostMapping("/signin")
    public String addUser(User user){
        User userFromDb = userRepository.findByName(user.getName());

        Logger LOG = Logger.getLogger(UserController.class);
        LOG.info(user);

        if(userFromDb != null){
            return "signin";
        }

        userRepository.save(user);

        return "redirect:/login";
    }
}
