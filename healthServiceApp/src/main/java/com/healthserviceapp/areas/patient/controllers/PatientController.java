package com.healthserviceapp.areas.patient.controllers;

import com.healthserviceapp.areas.patient.models.bindingModels.AddPatientBidingModel;
import com.healthserviceapp.areas.patient.services.PatientService;
import com.healthserviceapp.areas.users.models.bindingModels.RegisterDoctorBidingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/add")
    public String getAddPatientPage(@ModelAttribute AddPatientBidingModel addPatientBidingModel){
        return "patient/add";
    }

    @PostMapping("/add")
    public String addPatient(@Valid @ModelAttribute AddPatientBidingModel addPatientBidingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        this.patientService.add(addPatientBidingModel);

        return "redirect:/";
    }
}
