package com.healthserviceapp.areas.protocol.services;

import com.healthserviceapp.areas.protocol.models.bindingModels.AddDiagnosisBindingModel;
import com.healthserviceapp.areas.protocol.models.bindingModels.EditDiagnosisBindingModel;
import com.healthserviceapp.areas.protocol.models.viewModels.BasicDiagnosisViewModel;

import java.util.List;

public interface DiagnosisService {

    boolean doesCodeExist(String code);

    void addNewDiagnosis(AddDiagnosisBindingModel addDiagnosisBindingModel);

    List<BasicDiagnosisViewModel> getAllDiagnoses();

    void deleteDiagnosisById(Long id);

    EditDiagnosisBindingModel findDiagnosisById(Long id);

    void saveChanges(EditDiagnosisBindingModel editDiagnosisBindingModel);
}
