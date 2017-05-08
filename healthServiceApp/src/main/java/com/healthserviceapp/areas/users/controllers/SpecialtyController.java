package com.healthserviceapp.areas.users.controllers;

import com.healthserviceapp.areas.users.models.viewModels.BasicSpecialtyViewModel;
import com.healthserviceapp.areas.users.services.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    private SpecialityService specialityService;

    @Autowired
    public SpecialtyController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping("")
    public ResponseEntity<List<BasicSpecialtyViewModel>> getSpecialties(){
        List<BasicSpecialtyViewModel> specialitiesNames =
                this.specialityService.getAllSpecialitiesNames();

        if(specialitiesNames == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<BasicSpecialtyViewModel>> responseEntity
                = new ResponseEntity(specialitiesNames, HttpStatus.OK);

        return responseEntity;
    }
}
