package com.healthserviceapp.areas.protocol.controllers;

import com.healthserviceapp.areas.common.utils.PageTitles;
import com.healthserviceapp.areas.medicine.entities.Medicine;
import com.healthserviceapp.areas.medicine.models.viewModels.MedicineViewModel;
import com.healthserviceapp.areas.medicine.services.MedicineService;
import com.healthserviceapp.areas.protocol.models.bindingModels.AddProtocolBindingModel;
import com.healthserviceapp.areas.protocol.repositories.ProtocolRepository;
import com.healthserviceapp.areas.protocol.services.ProtocolService;
import com.healthserviceapp.areas.users.models.viewModels.TitleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/protocols")
public class ProtocolController {

    private ProtocolService protocolService;

    private MedicineService medicineService;

    @Autowired
    public ProtocolController(ProtocolService protocolService, MedicineService medicineService) {
        this.protocolService = protocolService;
        this.medicineService = medicineService;
    }

    @ModelAttribute("medicines")
    public List<MedicineViewModel> medicines(){
        return this.medicineService.getMedicines();
    }

    @GetMapping("/add")
    public String getAddProtocolPage(@ModelAttribute AddProtocolBindingModel addProtocolBindingModel, Model model){
        model.addAttribute("title", PageTitles.ADD_PROTOCOL);
        return "protocols/add";
    }
}
