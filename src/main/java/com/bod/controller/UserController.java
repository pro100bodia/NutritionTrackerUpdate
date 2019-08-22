package com.bod.controller;

import com.bod.domain.DeflectionRecord;
import com.bod.domain.Food;
import com.bod.domain.User;
import com.bod.dto.PlateItemDto;
import com.bod.repository.CoefRepository;
import com.bod.repository.FoodRepository;
import com.bod.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodRepository foodRepository;

    private static final List<PlateItemDto> foodList = new LinkedList<>();

    @PostMapping("/user")
    public String getFood(@RequestParam("food_selection") long foodId,
                          @RequestParam("food_amount") long amount,
                          Model model){
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

        DeflectionRecord norm = caluclateNorm();
        model.addAttribute("norm", norm);
        return "user";
    }

    @Autowired
    CoefRepository coefRepository;

    private DeflectionRecord caluclateNorm() {
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

        //get coeficients
        double[] coefs = new CoefsService().getCoefs(client.getGender());

//        LifeStyle lifeStyle = new ClientFacade().cacheLifeStyle(
                (String) req.getServletContext().getAttribute("lifeStyle"));

        calories = (coefs[0] + coefs[1] * (Double) req.getServletContext().getAttribute("weight") +
                coefs[2] * (Double) req.getServletContext().getAttribute("height") +
                coefs[3] * (Integer) req.getServletContext().getAttribute("age")) *
                lifeStyle.getValue();

        proteins = carbohydrates = calories / 4;
        fats = calories / 9;

        NutritiveValue norm = new NutritiveValue(calories, proteins, fats, carbohydrates);

        NutritiveValue deflection = eaten.subtract(norm);

        //write deflection
        DeflectionFacade deflectionFacade = new DeflectionFacade();
        deflectionFacade.writeDeflection(deflection, client.getId());

        //writing the plate
        DailyFacade dailyFacade = new DailyFacade();
        dailyFacade.writeToFoodHistory(dailyRation, client.getId());
        //cleaning the plate
        dailyRation.getFoodMap().clear();
        LOG.info("Client`s plate size in the end: " + client.getDailyRation().getFoodMap().size());

        return null;
    }

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
