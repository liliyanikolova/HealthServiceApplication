package com.healthserviceapp.areas.medicine.controllers;

import com.healthserviceapp.areas.medicine.exceptions.DozeNotFoundException;
import com.healthserviceapp.areas.medicine.exceptions.MedicineNotFoundException;
import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBindingModel;
import com.healthserviceapp.areas.medicine.models.bindingModels.EditMedicineBindingModel;
import com.healthserviceapp.areas.medicine.models.viewModels.BasicMedicineViewModel;
import com.healthserviceapp.areas.medicine.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public String getAddMedicinePage(@ModelAttribute AddMedicineBindingModel addMedicineBindingModel){
        return "medicines/add";
    }

    @PostMapping("/add")
    public String addMedicine(@Valid @ModelAttribute AddMedicineBindingModel addMedicineBindingModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "medicines/add";
        }

        this.medicineService.addNewMedicine(addMedicineBindingModel);

        return "redirect:/medicines";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Long id) {
        this.medicineService.deleteMedicineById(id);

        return "redirect:/medicines";
    }

    @GetMapping("/edit/{id}")
    public String getEditMedicinePage(@PathVariable Long id, Model model){
        EditMedicineBindingModel editMedicineBindingModel = this.medicineService.findMedicineById(id);
        model.addAttribute(editMedicineBindingModel);

        return "medicines/edit";
    }

    @PostMapping("/edit/{id}")
    public String editMedicine(HttpServletRequest httpServletRequest,@PathVariable Long id){
//        if (bindingResult.hasErrors()){
//            return "medicines/edit/" + id;
//        }

        EditMedicineBindingModel editMedicineBindingModel = new EditMedicineBindingModel();
        editMedicineBindingModel.setId(id);
        this.medicineService.saveChanges(editMedicineBindingModel, httpServletRequest);

        return "redirect:/medicines";
    }

    @ExceptionHandler(MedicineNotFoundException.class)
    public String catchMedicineNotFoundException() {

        return "exceptions/medicine-not-found";
    }

    @ExceptionHandler(DozeNotFoundException.class)
    public String catchDozeNotFoundException() {

        return "exceptions/doze-not-found";
    }
}
