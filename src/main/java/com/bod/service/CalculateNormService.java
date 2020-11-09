package com.bod.service;

import com.bod.domain.Coeficients;
import com.bod.domain.DeflectionRecord;
import com.bod.domain.User;
import com.bod.dto.PlateItemDto;
import com.bod.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateNormService {
    private static final Logger LOG = Logger.getLogger(CalculateNormService.class);

    private UserRepository userRepository;

    @Autowired
    public CalculateNormService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String perform(Model model, List<PlateItemDto> foodList, String username, DeflectionRecord deflection ){
        User user = userRepository.findByName(username);

        model.addAttribute("user", user);

        LOG.info("Calculating eaten nutritive value");

        double calories = 0.0, proteins = 0.0, fats = 0.0, carbohydrates = 0.0;
        for (PlateItemDto foodItem : foodList) {
            calories += foodItem.getFood().getCalories() * foodItem.getAmount();
            proteins += foodItem.getFood().getProtein() * foodItem.getAmount();
            fats += foodItem.getFood().getFats() * foodItem.getAmount();
            carbohydrates += foodItem.getFood().getCarbohydrates() * foodItem.getAmount();
        }
        DeflectionRecord eaten = new DeflectionRecord();

        eaten.setCalories(calories);
        eaten.setProtein(proteins);
        eaten.setFats(fats);
        eaten.setCarbohydrates(carbohydrates);

        LOG.info("Daily nutritive value is: " + eaten);

        LOG.info("Calculating client`s norm");
        LOG.info("Getting coefs from database");

        //todo get coeficients from repository
        List<Coeficients> coefsList = new ArrayList<>();

        double []coefs = new double [4];
        for(Coeficients coeficient : coefsList){
            coefs[coeficient.getId()] = coeficient.getValByGender(user.getGender());
        }

        calories = (coefs[0] + coefs[1] * user.getWeight() +
                coefs[2] * user.getHeight() +
                coefs[3] * (System.currentTimeMillis() - user.getBirthdate().getTime()) / 3.154e+10) *
                user.getLifeStyle().getValue();

        proteins = carbohydrates = calories / 4;
        fats = calories / 9;

        deflection.setCalories(eaten.getCalories() - calories);
        deflection.setProtein(eaten.getProtein() - proteins);
        deflection.setFats(eaten.getFats() - fats);
        deflection.setCarbohydrates(eaten.getCarbohydrates() - carbohydrates);


        LOG.info(deflection);
        model.addAttribute("def", deflection);

        foodList.clear();

        return "redirect:/user";
    }
}