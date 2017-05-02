package com.healthserviceapp.areas.users.controllers;

import com.healthserviceapp.areas.users.errors.Errors;
import com.healthserviceapp.areas.users.models.bindingModels.RegisterDoctorBidingModel;
import com.healthserviceapp.areas.users.models.viewModels.SpecialityViewModel;
import com.healthserviceapp.areas.users.models.viewModels.TitleViewModel;
import com.healthserviceapp.areas.users.services.SpecialityService;
import com.healthserviceapp.areas.users.services.TitleService;
import com.healthserviceapp.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private TitleService titleService;

    @ModelAttribute("titles")
    public Set<TitleViewModel> titles(){
        return this.titleService.getAllTitles();
    }

    @ModelAttribute("specialities")
    public Set<SpecialityViewModel> getSpecialityNames(){
        return this.specialityService.getAllSpecialities();
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegisterDoctorBidingModel registerDoctorBidingModel) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterDoctorBidingModel registerDoctorBidingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        this.userService.register(registerDoctorBidingModel);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }

        return "login";
    }
}
