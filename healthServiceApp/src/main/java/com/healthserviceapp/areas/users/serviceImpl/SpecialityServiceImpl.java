package com.healthserviceapp.areas.users.serviceImpl;

import com.healthserviceapp.areas.users.entities.Speciality;
import com.healthserviceapp.areas.users.models.bindingModels.SpecialtyBindingModel;
import com.healthserviceapp.areas.users.models.viewModels.BasicSpecialtyViewModel;
import com.healthserviceapp.areas.users.models.viewModels.SpecialityViewModel;
import com.healthserviceapp.areas.users.repositories.SpecialityRepository;
import com.healthserviceapp.areas.users.services.SpecialityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class SpecialityServiceImpl implements SpecialityService{

    private SpecialityRepository specialityRepository;

    private ModelMapper modelMapper;

    @Autowired
    public SpecialityServiceImpl(SpecialityRepository specialityRepository, ModelMapper modelMapper) {
        this.specialityRepository = specialityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SpecialityViewModel getSpecialityByName(String name) {
        Speciality speciality = this.specialityRepository.findOneByName(name);
        SpecialityViewModel specialityViewModel = this.modelMapper.map(speciality, SpecialityViewModel.class);

        return specialityViewModel;
    }

    @Override
    public Set<SpecialityViewModel> getAllSpecialities() {
        Set<SpecialityViewModel> specialityViewModels = new LinkedHashSet<>();
        List<Speciality> specialities = this.specialityRepository.findAll();

        for (Speciality speciality : specialities) {
            SpecialityViewModel specialityViewModel = this.modelMapper.map(speciality, SpecialityViewModel.class);
            specialityViewModels.add(specialityViewModel);
        }

        return specialityViewModels;
    }

    @Override
    public List<BasicSpecialtyViewModel> getAllSpecialitiesNames() {
        List<Speciality> specialities = this.specialityRepository.findAll();
        List<BasicSpecialtyViewModel> basicSpecialtyViewModels = new LinkedList<>();
        for (Speciality speciality : specialities) {
            BasicSpecialtyViewModel basicSpecialtyViewModel = this.modelMapper.map(speciality, BasicSpecialtyViewModel.class);
            basicSpecialtyViewModels.add(basicSpecialtyViewModel);
        }

        return basicSpecialtyViewModels;
    }

    @Override
    public List<SpecialtyBindingModel> findByDoctorId(Long id) {
        List<Speciality> specialities = this.specialityRepository.findByDoctorId(id);
        List<SpecialtyBindingModel> specialtyBindingModels = new LinkedList<>();
        for (Speciality speciality : specialities) {
            SpecialtyBindingModel specialtyBindingModel = this.modelMapper.map(speciality, SpecialtyBindingModel.class);
            specialtyBindingModels.add(specialtyBindingModel);
        }

        return specialtyBindingModels;
    }
}
