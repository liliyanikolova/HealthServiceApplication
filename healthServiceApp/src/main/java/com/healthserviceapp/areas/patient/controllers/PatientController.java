package com.healthserviceapp.areas.patient.controllers;

import com.healthserviceapp.areas.patient.entities.Patient;
import com.healthserviceapp.areas.patient.models.bindingModels.AddPatientBidingModel;
import com.healthserviceapp.areas.patient.models.bindingModels.EditPatientBindingModel;
import com.healthserviceapp.areas.patient.models.bindingModels.SearchPatientBidingModel;
import com.healthserviceapp.areas.patient.models.viewModels.BasicPatientViewModel;
import com.healthserviceapp.areas.patient.services.PatientService;
import com.healthserviceapp.areas.users.entities.User;
import com.healthserviceapp.areas.users.models.bindingModels.EditDoctorBidingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("")
    public String getAllPatientsPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BasicPatientViewModel> basicPatientViewModels = this.patientService.getLoggedDoctorPatients(user);
        model.addAttribute("basicPatientViewModels", basicPatientViewModels);

        return "patients/all";
    }

    @GetMapping("/add")
    public String getAddPatientPage(@ModelAttribute AddPatientBidingModel addPatientBidingModel){
        return "patients/add";
    }

    @PostMapping("/add")
    public String addPatient(@Valid @ModelAttribute AddPatientBidingModel addPatientBidingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patients/add";
        }

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        this.patientService.add(addPatientBidingModel, loggedUser);

        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String getEditPatientPage(@PathVariable Long id, Model model){
        EditPatientBindingModel editPatientBindingModel = this.patientService.findPatientById(id);
        model.addAttribute(editPatientBindingModel);

        return "patients/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPatient(@Valid @ModelAttribute EditPatientBindingModel editPatientBindingModel, @PathVariable Long id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "patients/edit";
        }

        editPatientBindingModel.setId(id);
        this.patientService.save(editPatientBindingModel);

        return "redirect:/patients";
    }

    @GetMapping("/search")
    public String getSearchPatientPage(@ModelAttribute SearchPatientBidingModel searchPatientBidingModel){
        return "patients/search";
    }

    @PostMapping("/search")
    public String searchPatient(@Valid @ModelAttribute SearchPatientBidingModel searchPatientBidingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patients/search";
        }

        String egn = searchPatientBidingModel.getEgn();
        if (this.patientService.doesEgnExist(egn)){
            Long id = this.patientService.findPatientByEgn(egn).getId();
            return "redirect:/patients/edit/" + id;
        }

        return "redirect:/patients/add";
    }
}
