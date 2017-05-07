package com.healthserviceapp.areas.protocol.serviceImpl;

import com.healthserviceapp.areas.protocol.entities.Diagnosis;
import com.healthserviceapp.areas.protocol.models.bindingModels.AddDiagnosisBindingModel;
import com.healthserviceapp.areas.protocol.repositories.DiagnosisRepository;
import com.healthserviceapp.areas.protocol.services.DiagnosisService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosisServiceImpl implements DiagnosisService{

    private DiagnosisRepository diagnosisRepository;

    private ModelMapper modelMapper;

    @Autowired
    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, ModelMapper modelMapper) {
        this.diagnosisRepository = diagnosisRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean doesCodeExist(String code) {
        Diagnosis patient = this.diagnosisRepository.findByCode(code);

        if (patient != null){
            return true;
        }

        return false;
    }

    @Override
    public void addNewMedicine(AddDiagnosisBindingModel addDiagnosisBindingModel) {
        Diagnosis diagnosis = this.modelMapper.map(addDiagnosisBindingModel, Diagnosis.class);

        this.diagnosisRepository.save(diagnosis);
    }
}
