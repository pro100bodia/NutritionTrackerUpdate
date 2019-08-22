package com.bod.controller;

import com.bod.domain.Food;
import com.bod.repository.FoodRepository;
import com.bod.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
public class PlateController {
    private static final Logger LOG = Logger.getLogger(PlateController.class);

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping(value = "/fill_plate")
    public String addToPlate(
            @RequestParam("type") long foodId,
            @RequestParam("type") double amount){
        LOG.info("/fill_plate is called");
        Food food = foodRepository.findById(foodId).orElse(null);
        LOG.info("Food in fill_plate mapping: " + food);

        return "<tr>" +
                "<td>" + food.getName() + "</td>" +
                "<td>" + food.getCalories() + "</td>" +
                "<td>" + food.getProtein() + "</td>" +
                "<td>" + food.getFats() + "</td>" +
                "<td>" + food.getCarbohydrates() + "</td>" +
                "<td>" + food.getNumber() + "</td>" +
                "<td>" + amount + "</td>" +
                "</tr>";
    }
}
