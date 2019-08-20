package com.bod.controller;

import com.bod.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
@PreAuthorize("hasAuthority('DOCTOR')")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping
    public String getDoctorPage(Model model){


        return "doctor";
    }


}
