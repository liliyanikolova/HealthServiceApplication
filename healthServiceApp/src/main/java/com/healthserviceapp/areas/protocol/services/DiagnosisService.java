package com.healthserviceapp.areas.protocol.services;

import com.healthserviceapp.areas.protocol.models.bindingModels.AddDiagnosisBindingModel;

public interface DiagnosisService {

    boolean doesCodeExist(String code);

    void addNewMedicine(AddDiagnosisBindingModel addDiagnosisBindingModel);
}
