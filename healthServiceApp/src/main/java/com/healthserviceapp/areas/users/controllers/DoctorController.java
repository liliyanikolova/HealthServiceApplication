package com.healthserviceapp.areas.users.controllers;

import com.healthserviceapp.areas.users.entities.User;
import com.healthserviceapp.areas.users.models.bindingModels.EditDoctorBidingModel;
import com.healthserviceapp.areas.users.models.bindingModels.RegisterDoctorBidingModel;
import com.healthserviceapp.areas.users.models.viewModels.SpecialityViewModel;
import com.healthserviceapp.areas.users.models.viewModels.TitleViewModel;
import com.healthserviceapp.areas.users.services.SpecialityService;
import com.healthserviceapp.areas.users.services.TitleService;
import com.healthserviceapp.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

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
        return "doctor-register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterDoctorBidingModel registerDoctorBidingModel, BindingResult bindingResult, @RequestParam() MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "doctor-register";
        }

        String a = file.getName();
        System.out.println(file.getName());

        this.userService.register(registerDoctorBidingModel);

        return "redirect:/";
    }

    @GetMapping("/edit/{userId}")
    public String getEditUserPage(@PathVariable Long userId, Model model){
        EditDoctorBidingModel editDoctorBidingModel = this.userService.findDoctorById(userId);
        model.addAttribute(editDoctorBidingModel);

        return "doctor-edit";
    }

    @PostMapping("/edit/{userId}")
    public String editUser(@Valid @ModelAttribute EditDoctorBidingModel editDoctorBidingModel, @PathVariable Long userId, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "doctor-edit";
        }

        editDoctorBidingModel.setId(userId);
        this.userService.save(editDoctorBidingModel);
        return "redirect:/";
    }


    @GetMapping("/delete/patient/{id}")
    public String deleteVirus(@PathVariable Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.userService.deletePatientById(id, user);
        return "redirect:/patients";
    }
}
