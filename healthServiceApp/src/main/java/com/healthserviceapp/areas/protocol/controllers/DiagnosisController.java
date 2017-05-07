package com.healthserviceapp.areas.protocol.controllers;

import com.healthserviceapp.areas.patient.models.bindingModels.EditPatientBindingModel;
import com.healthserviceapp.areas.protocol.exceptions.DiagnosisNotFoundException;
import com.healthserviceapp.areas.protocol.models.bindingModels.AddDiagnosisBindingModel;
import com.healthserviceapp.areas.protocol.models.bindingModels.EditDiagnosisBindingModel;
import com.healthserviceapp.areas.protocol.models.viewModels.BasicDiagnosisViewModel;
import com.healthserviceapp.areas.protocol.services.DiagnosisService;
import com.healthserviceapp.areas.users.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/diagnoses")
public class DiagnosisController {

    private DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping("")
    public String getAllDiagnoses(Model model){
        List<BasicDiagnosisViewModel> basicDiagnosisViewModels = this.diagnosisService.getAllDiagnoses();
        model.addAttribute("basicDiagnosisViewModels", basicDiagnosisViewModels);

        return "diagnoses/all";
    }

    @GetMapping("/add")
    public String getAddDiagnosisPage(@ModelAttribute AddDiagnosisBindingModel addDiagnosisBindingModel){
        return "diagnoses/add";
    }

    @PostMapping("/add")
    public String addDiagnosis(@Valid @ModelAttribute AddDiagnosisBindingModel addDiagnosisBindingModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "diagnoses/add";
        }

        this.diagnosisService.addNewDiagnosis(addDiagnosisBindingModel);

        return "redirect:/diagnoses";
    }

    @GetMapping("/delete/{id}")
    public String deleteDiagnosis(@PathVariable Long id) {
        this.diagnosisService.deleteDiagnosisById(id);

        return "redirect:/diagnoses";
    }

    @GetMapping("/edit/{id}")
    public String getEditDignosisPage(@PathVariable Long id, Model model){
        EditDiagnosisBindingModel editDiagnosisBindingModel = this.diagnosisService.findDiagnosisById(id);
        model.addAttribute(editDiagnosisBindingModel);

        return "diagnoses/edit";
    }

    @PostMapping("/edit/{id}")
    public String editDiagnosis(@PathVariable Long id, @Valid @ModelAttribute EditDiagnosisBindingModel editDiagnosisBindingModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "diagnoses/edit";
        }

        editDiagnosisBindingModel.setId(id);
        this.diagnosisService.saveChanges(editDiagnosisBindingModel);

        return "redirect:/diagnoses";
    }

    @ExceptionHandler(DiagnosisNotFoundException.class)
    public String catchPatientNotFoundException() {

        return "exceptions/diagnosis-not-found";
    }
}
