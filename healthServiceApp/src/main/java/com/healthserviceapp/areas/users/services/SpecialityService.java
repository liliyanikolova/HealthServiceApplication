package com.healthserviceapp.areas.users.services;

import com.healthserviceapp.areas.users.models.viewModels.BasicSpecialtyViewModel;
import com.healthserviceapp.areas.users.models.viewModels.SpecialityViewModel;

import java.util.List;
import java.util.Set;

public interface SpecialityService {

    SpecialityViewModel getSpecialityByName(String name);

    Set<SpecialityViewModel> getAllSpecialities();

    List<BasicSpecialtyViewModel> getAllSpecialitiesNames();
}
