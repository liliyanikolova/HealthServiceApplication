package com.healthserviceapp.areas.users.controllers;

import com.healthserviceapp.areas.common.utils.PageTitles;
import com.healthserviceapp.areas.users.entities.Title;
import com.healthserviceapp.areas.users.entities.User;
import com.healthserviceapp.areas.users.exceptions.TitleNotFoundException;
import com.healthserviceapp.areas.users.exceptions.UserNotFoundException;
import com.healthserviceapp.areas.users.models.bindingModels.EditDoctorBindingModel;
import com.healthserviceapp.areas.users.models.bindingModels.RegisterDoctorBindingModel;
import com.healthserviceapp.areas.users.models.bindingModels.SpecialtyBindingModel;
import com.healthserviceapp.areas.users.models.bindingModels.TitleBindingModel;
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
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private UserService userService;

    private SpecialityService specialityService;

    private TitleService titleService;

    @Autowired
    public DoctorController(UserService userService, SpecialityService specialityService, TitleService titleService) {
        this.userService = userService;
        this.specialityService = specialityService;
        this.titleService = titleService;
    }

    @ModelAttribute("titles")
    public Set<TitleViewModel> titles(){
        return this.titleService.getAllTitles();
    }

    @ModelAttribute("specialities")
    public Set<SpecialityViewModel> getSpecialityNames(){
        return this.specialityService.getAllSpecialities();
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegisterDoctorBindingModel registerDoctorBindingModel, Model model) {
        model.addAttribute("title", PageTitles.REGISTER_DOCTOR);
        return "doctor-register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterDoctorBindingModel registerDoctorBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "doctor-register";
        }

        this.userService.register(registerDoctorBindingModel);

        return "redirect:/";
    }

    @GetMapping("/edit")
    public String getEditUserPage(Model model){
        model.addAttribute("title", PageTitles.EDIT_DOCTOR);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();
        EditDoctorBindingModel editDoctorBindingModel = this.userService.findDoctorById(userId);

        TitleBindingModel title = this.titleService.findByDoctorId(userId);
        editDoctorBindingModel.setTitle(title.getName());

        List<SpecialtyBindingModel> specialtyBindingModels = this.specialityService.findByDoctorId(userId);
        for (int i = 0; i < specialtyBindingModels.size(); i++) {
            editDoctorBindingModel.getSpecialities()[i] = specialtyBindingModels.get(i).getName();
        }

        model.addAttribute(editDoctorBindingModel);

        return "doctor-edit";
    }

    @PostMapping("/edit")
    public String editUser(@Valid @ModelAttribute EditDoctorBindingModel editDoctorBindingModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "doctor-edit";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();
        editDoctorBindingModel.setId(userId);
        this.userService.saveChanges(editDoctorBindingModel);
        return "redirect:/";
    }

    @GetMapping("/delete/patient/{id}")
    public String deletePatient(@PathVariable Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.userService.deletePatientById(id, user);
        return "redirect:/patients";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String catchUserNotFoundException() {

        return "exceptions/user-not-found";
    }

    @ExceptionHandler(TitleNotFoundException.class)
    public String catchTitleNotFoundException() {

        return "exceptions/title-not-found";
    }
}
