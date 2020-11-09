package com.bod.service;

import com.bod.domain.DeflectionRecord;
import com.bod.domain.Food;
import com.bod.domain.User;
import com.bod.repository.FoodRepository;
import com.bod.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class UserContentService {
    private UserRepository userRepository;

    private FoodRepository foodRepository;

    @Autowired
    public UserContentService(UserRepository userRepository, FoodRepository foodRepository) {
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
    }

    public String perform(Model model, DeflectionRecord deflection){
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
        model.addAttribute("foodItems", foodItems);
        model.addAttribute("def", deflection);

        return "user";
    }
}
