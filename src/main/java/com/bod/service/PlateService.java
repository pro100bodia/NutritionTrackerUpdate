package com.bod.service;

import com.bod.domain.DeflectionRecord;
import com.bod.domain.Food;
import com.bod.domain.User;
import com.bod.dto.PlateItemDto;
import com.bod.repository.FoodRepository;
import com.bod.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PlateService {
    private FoodRepository foodRepository;

    private UserRepository userRepository;

    @Autowired
    public PlateService(FoodRepository foodRepository, UserRepository userRepository) {
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
    }

    public String perform(long foodId, long amount, Model model,
            List<PlateItemDto> foodList, DeflectionRecord deflection){
        PlateItemDto freshPlateItem = new PlateItemDto(
                foodRepository.findById(foodId).orElse(null), amount);

        foodList.add(freshPlateItem);

        model.addAttribute("foodList", foodList);



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
