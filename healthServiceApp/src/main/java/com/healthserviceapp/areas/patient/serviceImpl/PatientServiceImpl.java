package com.healthserviceapp.areas.patient.serviceImpl;

import com.healthserviceapp.areas.patient.entities.Patient;
import com.healthserviceapp.areas.patient.exceptions.PatientNotFoundException;
import com.healthserviceapp.areas.patient.models.bindingModels.AddPatientBindingModel;
import com.healthserviceapp.areas.patient.models.bindingModels.EditPatientBindingModel;
import com.healthserviceapp.areas.patient.models.viewModels.BasicPatientViewModel;
import com.healthserviceapp.areas.patient.repositories.PatientRepository;
import com.healthserviceapp.areas.patient.services.PatientService;
import com.healthserviceapp.areas.protocol.models.viewModels.BasicDiagnosisViewModel;
import com.healthserviceapp.areas.users.entities.Doctor;
import com.healthserviceapp.areas.users.entities.User;
import com.healthserviceapp.areas.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    public void add(AddPatientBindingModel addPatientBindingModel, User user) {
        Patient patient = this.modelMapper.map(addPatientBindingModel, Patient.class);

        Doctor doctor = (Doctor) user;
        doctor.getPatients().add(patient);

        this.userRepository.save(doctor);
    }

    @Override
    public EditPatientBindingModel findPatientById(Long id) {
        Patient patient = this.patientRepository.findOne(id);
        if (patient ==null){
            throw new PatientNotFoundException();
        }

        EditPatientBindingModel editPatientBindingModel = this.modelMapper.map(patient, EditPatientBindingModel.class);

        return editPatientBindingModel;
    }

    @Override
    public void save(EditPatientBindingModel editPatientBindingModel, User user) {
        if (this.patientRepository.findOne(editPatientBindingModel.getId()) == null){
            throw new PatientNotFoundException();
        }

        String egn = this.patientRepository.findOne(editPatientBindingModel.getId()).getEgn();
        Patient editPatient = this.modelMapper.map(editPatientBindingModel, Patient.class);
        editPatient.setEgn(egn);
        this.patientRepository.saveAndFlush(editPatient);

        Doctor doctor = (Doctor) user;
        Set<Patient> patients = doctor.getPatients();
        for (Patient patient : patients) {
            if (patient.getId() == editPatient.getId()){
                return;
            }
        }

        doctor.getPatients().add(editPatient);

        this.userRepository.save(doctor);
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

    @Override
    public List<BasicPatientViewModel> getLoggedDoctorPatients(User user) {
        List<Patient> patients = this.patientRepository.findLoggedDoctorPatients(user.getId());
        List<BasicPatientViewModel> basicPatientViewModels = new LinkedList<>();
        for (Patient patient : patients) {
            BasicPatientViewModel basicPatientViewModel = this.modelMapper.map(patient, BasicPatientViewModel.class);
            basicPatientViewModels.add(basicPatientViewModel);
        }

        return basicPatientViewModels;
    }


    @Override
    public boolean doesEgnExist(String egn) {
        Patient patient = this.patientRepository.findByEgn(egn);

        if (patient != null){
            return true;
        }

        return false;
    }

    @Override
    public Patient findPatientByEgn(String egn) {
        Patient patient = this.patientRepository.findByEgn(egn);
        if (patient == null){
            throw new PatientNotFoundException();
        }

        return patient;
    }

    @Override
    public List<BasicPatientViewModel> searchByEgn(String egn, User user) {
        List<Patient> patients = this.patientRepository.searchByEgn(egn, user.getId());
        List<BasicPatientViewModel> basicPatientViewModels = new LinkedList<>();
        for (Patient patient : patients) {
            BasicPatientViewModel basicPatientViewModel = this.modelMapper.map(patient, BasicPatientViewModel.class);
            basicPatientViewModels.add(basicPatientViewModel);
        }

        return basicPatientViewModels;
    }

}
