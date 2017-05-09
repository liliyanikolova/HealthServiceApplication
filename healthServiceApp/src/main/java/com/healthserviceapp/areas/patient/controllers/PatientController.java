package com.healthserviceapp.areas.patient.controllers;

import com.healthserviceapp.areas.common.utils.PageTitles;
import com.healthserviceapp.areas.patient.exceptions.PatientNotFoundException;
import com.healthserviceapp.areas.patient.models.bindingModels.AddPatientBindingModel;
import com.healthserviceapp.areas.patient.models.bindingModels.EditPatientBindingModel;
import com.healthserviceapp.areas.patient.models.bindingModels.SearchPatientBindingModel;
import com.healthserviceapp.areas.patient.models.viewModels.BasicPatientViewModel;
import com.healthserviceapp.areas.patient.services.PatientService;
import com.healthserviceapp.areas.users.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<BasicPatientViewModel> basicPatientViewModels = this.patientService.getLoggedDoctorPatients(user);
//        model.addAttribute("basicPatientViewModels", basicPatientViewModels);
        model.addAttribute("title", PageTitles.ALL_PATIENTS);
        return "patients/all";
    }

    @GetMapping("/add")
    public String getAddPatientPage(@ModelAttribute AddPatientBindingModel addPatientBindingModel, Model model){
        model.addAttribute("title", PageTitles.ADD_PATIENT);
        return "patients/add";
    }

    @PostMapping("/add")
    public String addPatient(@Valid @ModelAttribute AddPatientBindingModel addPatientBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patients/add";
        }

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        this.patientService.add(addPatientBindingModel, loggedUser);

        return "redirect:/patients";
    }

    @GetMapping("/add/{egn}")
    public String getAddPatientPage(@PathVariable String egn, @ModelAttribute AddPatientBindingModel addPatientBindingModel, Model model){
        model.addAttribute("title", PageTitles.ADD_PATIENT);
        addPatientBindingModel.setEgn(egn);
        return "patients/add";
    }

    @PostMapping("/add/{egn}")
    public String addPatient(@PathVariable String egn, @Valid @ModelAttribute AddPatientBindingModel addPatientBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patients/add";
        }

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        this.patientService.add(addPatientBindingModel, loggedUser);

        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String getEditPatientPage(@PathVariable Long id, Model model){
        model.addAttribute("title", PageTitles.EDIT_PATIENT);
        EditPatientBindingModel editPatientBindingModel = this.patientService.findPatientById(id);
        model.addAttribute(editPatientBindingModel);

        return "patients/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPatient(@PathVariable Long id, @Valid @ModelAttribute EditPatientBindingModel editPatientBindingModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "patients/edit";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        editPatientBindingModel.setId(id);
        this.patientService.save(editPatientBindingModel, user);

        return "redirect:/patients";
    }

    @GetMapping("/search")
    public String getSearchPatientPage(@ModelAttribute SearchPatientBindingModel searchPatientBindingModel, Model model){
        model.addAttribute("title", PageTitles.ADD_PATIENT);
        return "patients/search";
    }

    @PostMapping("/search")
    public String searchPatient(@Valid @ModelAttribute SearchPatientBindingModel searchPatientBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patients/search";
        }

        String egn = searchPatientBindingModel.getEgn();
        if (this.patientService.doesEgnExist(egn)){
            Long id = this.patientService.findPatientByEgn(egn).getId();
            return "redirect:/patients/edit/" + id;
        }

        return "redirect:/patients/add/" + egn;
    }

    @GetMapping("/searchAsync")
    public ResponseEntity<List<BasicPatientViewModel>> search(@RequestParam String egn){
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BasicPatientViewModel> toDoItemViewModels = this.patientService.searchByEgn(egn, loggedUser);
        return new ResponseEntity(toDoItemViewModels, HttpStatus.OK);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public String catchPatientNotFoundException() {

        return "exceptions/patient-not-found";
    }
}
