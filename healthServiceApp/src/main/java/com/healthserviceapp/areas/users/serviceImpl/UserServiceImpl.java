package com.healthserviceapp.areas.users.serviceImpl;

import com.healthserviceapp.areas.common.utils.Constants;
import com.healthserviceapp.areas.patient.entities.Patient;
import com.healthserviceapp.areas.patient.exceptions.PatientNotFoundException;
import com.healthserviceapp.areas.users.entities.*;
import com.healthserviceapp.areas.users.exceptions.UserNotFoundException;
import com.healthserviceapp.areas.users.models.bindingModels.EditDoctorBindingModel;
import com.healthserviceapp.areas.users.models.bindingModels.RegisterDoctorBindingModel;
import com.healthserviceapp.areas.users.repositories.RoleRepository;
import com.healthserviceapp.areas.users.repositories.SpecialityRepository;
import com.healthserviceapp.areas.users.repositories.TitleRepository;
import com.healthserviceapp.areas.users.repositories.UserRepository;
import com.healthserviceapp.areas.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByEmail(email);
        if (user == null){
            throw new UserNotFoundException();
        }

        return user;
    }

    @Override
    public void register(RegisterDoctorBindingModel registerDoctorBindingModel) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(registerDoctorBindingModel.getFirstName());
        doctor.setLastName(registerDoctorBindingModel.getLastName());
        doctor.setUin(registerDoctorBindingModel.getUin());
        doctor.setEmail(registerDoctorBindingModel.getEmail());

        Role role = this.roleRepository.findOneByAuthority(Constants.DOCTOR_ROLE);
        doctor.addRole(role);

        Title title = this.titleRepository.findByName(registerDoctorBindingModel.getTitle());
        doctor.setTitle(title);

        String encryptedPassword = this.bCryptPasswordEncoder.encode(registerDoctorBindingModel.getPassword());
        doctor.setPassword(encryptedPassword);
        doctor.setAccountNonExpired(true);
        doctor.setAccountNonLocked(true);
        doctor.setCredentialsNonExpired(true);
        doctor.setEnabled(true);

        Set<Speciality> specialities = this.specialityRepository.findAllByNameIn(registerDoctorBindingModel.getSpecialities());
        doctor.setSpecialities(specialities);

        this.userRepository.save(doctor);
    }

    @Override
    public boolean doesEmailExist(String email) {
        User user = this.userRepository.findOneByEmail(email);
        if (user != null){
            return true;
        }

        return false;
    }

    @Override
    public EditDoctorBindingModel findDoctorById(Long id) {
        User user = this.userRepository.findOne(id);
        if (user == null){
            throw new UserNotFoundException();
        }

        EditDoctorBindingModel editDoctorBindingModel = this.modelMapper.map(user, EditDoctorBindingModel.class);

        return editDoctorBindingModel;
    }

    @Override
    public void saveChanges(EditDoctorBindingModel editDoctorBindingModel) {
        Doctor doctor = this.userRepository.findById(editDoctorBindingModel.getId());
        if (doctor == null){
            throw new UserNotFoundException();
        }

        doctor.setFirstName(editDoctorBindingModel.getFirstName());
        doctor.setLastName(editDoctorBindingModel.getLastName());
        doctor.setUin(editDoctorBindingModel.getUin());

        Role role = this.roleRepository.findOneByAuthority(Constants.DOCTOR_ROLE);
        doctor.addRole(role);

        Title title = this.titleRepository.findByName(editDoctorBindingModel.getTitle());
        if (title != null){
            doctor.setTitle(title);
        }

        Set<Speciality> specialities = this.specialityRepository.findAllByNameIn(editDoctorBindingModel.getSpecialities());
        if (specialities.size() != 0){
            doctor.setSpecialities(specialities);
        }

        this.userRepository.save(doctor);
    }

    @Override
    public void deletePatientById(Long patientId, User user) {
        if (user == null){
            throw new UserNotFoundException();
        }

        Doctor doctor = (Doctor) user;
        Set<Patient> patients = doctor.getPatients().stream()
                                    .filter(p -> p.getId() != patientId)
                                    .collect(Collectors.toSet());

        if (patients ==null){
            throw new PatientNotFoundException();
        }

        doctor.setPatients(patients);
        this.userRepository.saveAndFlush(doctor);
    }
}
