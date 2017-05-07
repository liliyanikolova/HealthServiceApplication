package com.healthserviceapp.areas.protocol.controllers;

import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBindingModel;
import com.healthserviceapp.areas.protocol.models.bindingModels.AddDiagnosisBindingModel;
import com.healthserviceapp.areas.protocol.services.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/diagnosis")
public class DiagnosisController {

    private DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping("/add")
    public String getAddDiagnosisPage(@ModelAttribute AddDiagnosisBindingModel addDiagnosisBindingModel){
        return "diagnosis/add";
    }

    @PostMapping("/add")
    public String addMedicine(@Valid @ModelAttribute AddDiagnosisBindingModel addDiagnosisBindingModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "diagnosis/add";
        }

        this.diagnosisService.addNewMedicine(addDiagnosisBindingModel);

        return "redirect:/diagnosis";
    }
}
