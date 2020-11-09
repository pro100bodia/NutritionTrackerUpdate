package com.bod.controller;

import com.bod.domain.DeflectionRecord;
import com.bod.dto.PlateItemDto;
import com.bod.service.CalculateNormService;
import com.bod.service.PlateService;
import com.bod.service.UserContentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class);

    private CalculateNormService calculateNormService;

    private PlateService plateService;

    private UserContentService userContentService;

    @Autowired
    public UserController(CalculateNormService calculateNormService, PlateService plateService, UserContentService userContentService){
        this.calculateNormService = calculateNormService;
        this.plateService = plateService;
        this.userContentService = userContentService;
    }

    private static final List<PlateItemDto> foodList = new LinkedList<>();
    private static final DeflectionRecord deflection = new DeflectionRecord();

    @GetMapping("/calc")
    private String calculateNorm(@RequestParam("username")String username, Model model) {
        return calculateNormService.perform(model, foodList,
                username, deflection );
    }

    @PostMapping("/user")
    public String fillPlate(@RequestParam("food_selection") long foodId,
                          @RequestParam("food_amount") long amount,
                          Model model){
        return plateService.perform(foodId, amount, model,
                foodList, deflection);
    }

    @GetMapping("/user")
    public String getContent(Model model){
        return userContentService.perform(model, deflection);
    }
}
