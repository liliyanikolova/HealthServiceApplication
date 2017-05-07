package com.healthserviceapp.areas.protocol.controllers;

import com.healthserviceapp.areas.protocol.models.bindingModels.AddProtocolBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/protocols")
public class ProtocolController {

    @GetMapping("/add")
    public String getAddProtocolPage(@ModelAttribute AddProtocolBindingModel addProtocolBindingModel){
        return "protocols/add";
    }
}
