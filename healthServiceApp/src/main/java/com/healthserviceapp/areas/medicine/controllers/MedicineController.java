package com.healthserviceapp.areas.medicine.controllers;

import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBidingModel;
import com.healthserviceapp.areas.medicine.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/medicines")
public class MedicineController {

    private MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    //    @GetMapping("")
//    public String getAllMedicines(){
//        return "medicines/all";
//    }

    @GetMapping("/add")
    public String getAddMedicinePage(@ModelAttribute AddMedicineBidingModel addMedicineBidingModel){
        return "medicines/add";
    }

    @PostMapping("/add")
    public String addMedicine(@Valid @ModelAttribute AddMedicineBidingModel addMedicineBidingModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "medicines/add";
        }



        return "redirect:/medicines";
    }
}
