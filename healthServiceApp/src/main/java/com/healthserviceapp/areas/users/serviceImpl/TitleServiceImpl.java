package com.healthserviceapp.areas.users.serviceImpl;

import com.healthserviceapp.areas.users.entities.Title;
import com.healthserviceapp.areas.users.exceptions.TitleNotFoundException;
import com.healthserviceapp.areas.users.models.bindingModels.TitleBindingModel;
import com.healthserviceapp.areas.users.models.viewModels.TitleViewModel;
import com.healthserviceapp.areas.users.repositories.TitleRepository;
import com.healthserviceapp.areas.users.services.TitleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class TitleServiceImpl implements TitleService{

    private TitleRepository titleRepository;

    private ModelMapper modelMapper;

    @Autowired
    public TitleServiceImpl(TitleRepository titleRepository, ModelMapper modelMapper) {
        this.titleRepository = titleRepository;
        this.modelMapper = modelMapper;
    }

    public Set<TitleViewModel> getAllTitles(){
        Set<TitleViewModel> titleViewModels = new LinkedHashSet<>();
        List<Title> titles = this.titleRepository.findAll();
        for (Title title : titles) {
            TitleViewModel titleViewModel = this. modelMapper.map(title, TitleViewModel.class);
            titleViewModels.add(titleViewModel);
        }

        return titleViewModels;
    }

    @Override
    public TitleViewModel findByName(String name) {
        Title title = this.titleRepository.findByName(name);
        TitleViewModel titleViewModel = this.modelMapper.map(title, TitleViewModel.class);

        return titleViewModel;
    }

    @Override
    public TitleBindingModel findByDoctorId(Long id) {
        Title title = this.titleRepository.findByDoctorId(id);
        if (title == null){
            throw new TitleNotFoundException();
        }

        TitleBindingModel titleViewModel = this.modelMapper.map(title, TitleBindingModel.class);

        return titleViewModel;
    }
}
