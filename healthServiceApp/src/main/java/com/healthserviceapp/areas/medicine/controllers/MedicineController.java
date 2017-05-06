package com.healthserviceapp.areas.medicine.controllers;

import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBidingModel;
import com.healthserviceapp.areas.medicine.models.bindingModels.EditMedicineBidingModel;
import com.healthserviceapp.areas.medicine.models.viewModels.BasicMedicineViewModel;
import com.healthserviceapp.areas.medicine.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/medicines")
public class MedicineController {

    private MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("")
    public String getAllMedicines(Model model){
        List<BasicMedicineViewModel> basicMedicineViewModels = this.medicineService.getAllMedicines();
        model.addAttribute("basicMedicineViewModels", basicMedicineViewModels);

        return "medicines/all";
    }

    @GetMapping("/add")
    public String getAddMedicinePage(@ModelAttribute AddMedicineBidingModel addMedicineBidingModel){
        return "medicines/add";
    }

    @PostMapping("/add")
    public String addMedicine(@Valid @ModelAttribute AddMedicineBidingModel addMedicineBidingModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "medicines/add";
        }

        this.medicineService.addNewMedicine(addMedicineBidingModel);

        return "redirect:/medicines";
    }

    @GetMapping("/delete/{id}")
    public String deleteVirus(@PathVariable Long id) {
        this.medicineService.deleteMedicineById(id);

        return "redirect:/medicines";
    }

    @GetMapping("/edit/{id}")
    public String getEditMedicinePage(@PathVariable Long id, Model model){
        EditMedicineBidingModel editMedicineBidingModel = this.medicineService.findMedicineById(id);
        model.addAttribute(editMedicineBidingModel);

        return "medicines/edit";
    }
}
