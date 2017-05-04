package com.healthserviceapp.areas.patient.services;

import com.healthserviceapp.areas.patient.models.bindingModels.AddPatientBidingModel;
import com.healthserviceapp.areas.patient.models.bindingModels.EditPatientBindingModel;
import com.healthserviceapp.areas.patient.models.viewModels.BasicPatientViewModel;
import com.healthserviceapp.areas.users.entities.User;

import java.util.List;

public interface PatientService {

    void add(AddPatientBidingModel addPatientBidingModel, User user);

    EditPatientBindingModel findPatientById(Long id);

    void save(EditPatientBindingModel editPatientBindingModel);

    List<BasicPatientViewModel> getAllPatients();
}
