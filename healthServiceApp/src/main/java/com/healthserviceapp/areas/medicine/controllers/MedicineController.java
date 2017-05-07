package com.healthserviceapp.areas.medicine.controllers;

import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBidingModel;
import com.healthserviceapp.areas.medicine.models.bindingModels.EditMedicineBidingModel;
import com.healthserviceapp.areas.medicine.models.bindingModels.SearchMedicineBidingModel;
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

    @GetMapping("/add/{code}")
    public String getAddMedicinePage(@ModelAttribute AddMedicineBidingModel addMedicineBidingModel, @PathVariable String code){
        addMedicineBidingModel.setCode(code);
        return "medicines/add";
    }

    @PostMapping("/add/{code}")
    public String addMedicine(@Valid @ModelAttribute AddMedicineBidingModel addMedicineBidingModel, BindingResult bindingResult, @PathVariable String code){
        if (bindingResult.hasErrors()){
            return "medicines/add/" + code;
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

    @PostMapping("/edit/{id}")
    public String editMedicine(@Valid @ModelAttribute EditMedicineBidingModel editMedicineBidingModel, @PathVariable Long id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "medicines/edit";
        }

        this.medicineService.saveChanges(editMedicineBidingModel);

        return "redirect:/medicines";
    }

    @GetMapping("/search")
    public String getSearchMedicinePage(@ModelAttribute SearchMedicineBidingModel searchMedicineBidingModel){
        return "medicines/search";
    }

    @PostMapping("/search")
    public String searchPatient(@Valid @ModelAttribute SearchMedicineBidingModel searchMedicineBidingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patients/search";
        }

        String code = searchMedicineBidingModel.getCode();
        if (this.medicineService.doesCodeExist(code)){
            Long id = this.medicineService.findMedicineIdByCode(code);
            return "redirect:/medicines/edit/" + id;
        }

        return "redirect:/medicines/add/" + code;
    }
}
