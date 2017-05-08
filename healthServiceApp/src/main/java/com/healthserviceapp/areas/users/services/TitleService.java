package com.healthserviceapp.areas.users.services;

import com.healthserviceapp.areas.users.entities.Title;
import com.healthserviceapp.areas.users.models.bindingModels.TitleBindingModel;
import com.healthserviceapp.areas.users.models.viewModels.TitleViewModel;

import java.util.Set;

public interface TitleService {

    Set<TitleViewModel> getAllTitles();

    TitleViewModel findByName(String name);

    TitleBindingModel findByDoctorId(Long id);
}
