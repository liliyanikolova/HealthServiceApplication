package com.healthserviceapp.areas.patient.serviceImpl;

import com.healthserviceapp.areas.patient.entities.Patient;
import com.healthserviceapp.areas.patient.models.bindingModels.AddPatientBidingModel;
import com.healthserviceapp.areas.patient.repositories.PatientRepository;
import com.healthserviceapp.areas.patient.services.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(AddPatientBidingModel addPatientBidingModel) {
        Patient patient = this.modelMapper.map(addPatientBidingModel, Patient.class);

        this.patientRepository.saveAndFlush(patient);
    }
}
