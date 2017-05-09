package com.healthserviceapp.controllers;

import com.healthserviceapp.areas.common.utils.PageTitles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("title", PageTitles.HOME);
        return "home";
    }

}
