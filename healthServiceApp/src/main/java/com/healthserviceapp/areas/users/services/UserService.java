package com.healthserviceapp.areas.users.services;

import com.healthserviceapp.areas.users.entities.User;
import com.healthserviceapp.areas.users.models.bindingModels.EditDoctorBidingModel;
import com.healthserviceapp.areas.users.models.bindingModels.RegisterDoctorBidingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void register(RegisterDoctorBidingModel registerDoctorBidingModel);

    boolean doesEmailExist(String email);

    EditDoctorBidingModel findDoctorById(Long id);

    void save(EditDoctorBidingModel editDoctorBidingModel);

    void deletePatientById(Long patientId, User user);
}
