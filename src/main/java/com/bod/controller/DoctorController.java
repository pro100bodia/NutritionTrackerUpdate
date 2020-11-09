package com.bod.controller;

import com.bod.dto.UserStatDto;
import com.bod.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/doctor")
@PreAuthorize("hasAuthority('DOCTOR')")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping
    public String getDoctorPage(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

//        Page<UserStatDto> statPage = doctorRepository
//                .readAllUsersStat(PageRequest.of(currentPage, pageSize));

        List<UserStatDto> statPage = doctorRepository.readAllUsersStat();

        model.addAttribute("statPage", statPage);

        //generate page numbers list
//        int totalPages = statPage.getTotalPages();
//        if(totalPages > 0){
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }

        return "doctor";
    }


}
