package com.healthserviceapp.areas.users.services;

import com.healthserviceapp.areas.users.entities.User;
import com.healthserviceapp.areas.users.models.bindingModels.EditDoctorBindingModel;
import com.healthserviceapp.areas.users.models.bindingModels.RegisterDoctorBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void register(RegisterDoctorBindingModel registerDoctorBindingModel);

    boolean doesEmailExist(String email);

    EditDoctorBindingModel findDoctorById(Long id);

    void saveChanges(EditDoctorBindingModel editDoctorBindingModel);

    void deletePatientById(Long patientId, User user);
}
