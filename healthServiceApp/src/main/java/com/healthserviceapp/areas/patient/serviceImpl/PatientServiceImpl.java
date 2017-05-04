package com.healthserviceapp.areas.patient.serviceImpl;

import com.healthserviceapp.areas.patient.entities.Patient;
import com.healthserviceapp.areas.patient.models.bindingModels.AddPatientBidingModel;
import com.healthserviceapp.areas.patient.models.bindingModels.EditPatientBindingModel;
import com.healthserviceapp.areas.patient.models.viewModels.BasicPatientViewModel;
import com.healthserviceapp.areas.patient.repositories.PatientRepository;
import com.healthserviceapp.areas.patient.services.PatientService;
import com.healthserviceapp.areas.users.entities.Doctor;
import com.healthserviceapp.areas.users.entities.User;
import com.healthserviceapp.areas.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(AddPatientBidingModel addPatientBidingModel, User user) {
        Patient patient = this.modelMapper.map(addPatientBidingModel, Patient.class);

        Doctor doctor = (Doctor) user;
        doctor.getPatients().add(patient);

        this.userRepository.save(doctor);
    }

    @Override
    public EditPatientBindingModel findPatientById(Long id) {
        Patient patient = this.patientRepository.findOne(id);
        EditPatientBindingModel editPatientBindingModel = this.modelMapper.map(patient, EditPatientBindingModel.class);

        return editPatientBindingModel;
    }

    @Override
    public void save(EditPatientBindingModel editPatientBindingModel) {
        Patient patient = this.modelMapper.map(editPatientBindingModel, Patient.class);
        this.patientRepository.saveAndFlush(patient);
    }

    @Override
    public List<BasicPatientViewModel> getAllPatients() {
        List<Patient> patients = this.patientRepository.findAll();
        List<BasicPatientViewModel> basicPatientViewModels = new LinkedList<>();
        for (Patient patient : patients) {
            BasicPatientViewModel basicPatientViewModel = this.modelMapper.map(patient, BasicPatientViewModel.class);
            basicPatientViewModels.add(basicPatientViewModel);
        }

        return basicPatientViewModels;
    }
}
