package com.healthserviceapp.areas.patient.services;

import com.healthserviceapp.areas.patient.entities.Patient;
import com.healthserviceapp.areas.patient.models.bindingModels.AddPatientBindingModel;
import com.healthserviceapp.areas.patient.models.bindingModels.EditPatientBindingModel;
import com.healthserviceapp.areas.patient.models.viewModels.BasicPatientViewModel;
import com.healthserviceapp.areas.users.entities.User;

import java.util.List;

public interface PatientService {

    void add(AddPatientBindingModel addPatientBindingModel, User user);

    EditPatientBindingModel findPatientById(Long id);

    void save(EditPatientBindingModel editPatientBindingModel, User user);

    List<BasicPatientViewModel> getAllPatients();

    List<BasicPatientViewModel> getLoggedDoctorPatients(User user);

    boolean doesEgnExist(String egn);

    Patient findPatientByEgn(String egn);

}
