package com.bod.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice("com.bod.controller")
public class MyControllerAdvice {

    @ModelAttribute
    public void myMethod(@RequestParam String mandatoryParam,
                         Model model) {
        model.addAttribute("language", mandatoryParam);
    }
}
