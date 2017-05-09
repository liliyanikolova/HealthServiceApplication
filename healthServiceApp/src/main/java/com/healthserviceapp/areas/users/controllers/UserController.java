package com.healthserviceapp.areas.users.controllers;

import com.healthserviceapp.areas.common.utils.PageTitles;
import com.healthserviceapp.areas.users.errors.Errors;
import com.healthserviceapp.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("title", PageTitles.LOGIN);
        if(error != null){
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }

        return "login";
    }
}
