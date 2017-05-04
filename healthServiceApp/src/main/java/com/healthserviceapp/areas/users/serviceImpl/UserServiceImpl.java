package com.healthserviceapp.areas.users.serviceImpl;

import com.healthserviceapp.areas.common.utils.Constants;
import com.healthserviceapp.areas.patient.entities.Patient;
import com.healthserviceapp.areas.users.entities.*;
import com.healthserviceapp.areas.users.models.bindingModels.EditDoctorBidingModel;
import com.healthserviceapp.areas.users.models.bindingModels.RegisterDoctorBidingModel;
import com.healthserviceapp.areas.users.models.viewModels.SpecialityViewModel;
import com.healthserviceapp.areas.users.models.viewModels.TitleViewModel;
import com.healthserviceapp.areas.users.repositories.RoleRepository;
import com.healthserviceapp.areas.users.repositories.SpecialityRepository;
import com.healthserviceapp.areas.users.repositories.TitleRepository;
import com.healthserviceapp.areas.users.repositories.UserRepository;
import com.healthserviceapp.areas.users.services.SpecialityService;
import com.healthserviceapp.areas.users.services.TitleService;
import com.healthserviceapp.areas.users.services.UserService;
import com.healthserviceapp.areas.users.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
        //TODO Handle Exception

        return user;
    }

    @Override
    public void register(RegisterDoctorBidingModel registerDoctorBidingModel) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(registerDoctorBidingModel.getFirstName());
        doctor.setLastName(registerDoctorBidingModel.getLastName());
        doctor.setUin(registerDoctorBidingModel.getUin());
        doctor.setEmail(registerDoctorBidingModel.getEmail());

        Role role = this.roleRepository.findOneByAuthority(Constants.DOCTOR_ROLE);
        doctor.addRole(role);

        Title title = this.titleRepository.findByName(registerDoctorBidingModel.getTitle());
        doctor.setTitle(title);

        String encryptedPassword = this.bCryptPasswordEncoder.encode(registerDoctorBidingModel.getPassword());
        doctor.setPassword(encryptedPassword);
        doctor.setAccountNonExpired(true);
        doctor.setAccountNonLocked(true);
        doctor.setCredentialsNonExpired(true);
        doctor.setEnabled(true);

        Set<Speciality> specialities = this.specialityRepository.findAllByNameIn(registerDoctorBidingModel.getSpecialities());
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
    public EditDoctorBidingModel findDoctorById(Long id) {
        User user = this.userRepository.findOne(id);
        EditDoctorBidingModel editDoctorBidingModel = this.modelMapper.map(user, EditDoctorBidingModel.class);

        return editDoctorBidingModel;
    }

    @Override
    public void save(EditDoctorBidingModel editDoctorBidingModel) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(editDoctorBidingModel.getFirstName());
        doctor.setLastName(editDoctorBidingModel.getLastName());
        doctor.setUin(editDoctorBidingModel.getUin());

        Role role = this.roleRepository.findOneByAuthority(Constants.DOCTOR_ROLE);
        doctor.addRole(role);

        Title title = this.titleRepository.findByName(editDoctorBidingModel.getTitle());
        doctor.setTitle(title);

        Set<Speciality> specialities = this.specialityRepository.findAllByNameIn(editDoctorBidingModel.getSpecialities());
        doctor.setSpecialities(specialities);

        this.userRepository.save(doctor);
    }

    @Override
    public void deletePatientById(Long patientId, User user) {
        Doctor doctor = (Doctor) user;
        Set<Patient> patients = doctor.getPatients().stream()
                                    .filter(p -> p.getId() != patientId)
                                    .collect(Collectors.toSet());
        doctor.setPatients(patients);
        this.userRepository.saveAndFlush(doctor);
    }
}
