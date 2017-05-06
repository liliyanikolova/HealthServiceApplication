package com.healthserviceapp.controllers;

import com.healthserviceapp.areas.common.utils.Constants;
import com.healthserviceapp.areas.users.entities.Doctor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class AttributeController {

    @ModelAttribute("name")
    public String getName(){
        String name = Constants.DEFAULT_GREETING_NAME;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null){
            Object principal = authentication.getPrincipal();
            if (principal instanceof Doctor){
                Doctor doctor = (Doctor) principal;
                name = String.format("%s %s", doctor.getFirstName(), doctor.getLastName());
            }

        }

        return name;
    }
}
