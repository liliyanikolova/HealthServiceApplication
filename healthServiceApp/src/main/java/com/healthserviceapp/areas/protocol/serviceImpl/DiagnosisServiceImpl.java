package com.healthserviceapp.areas.protocol.serviceImpl;

import com.healthserviceapp.areas.medicine.models.viewModels.BasicMedicineViewModel;
import com.healthserviceapp.areas.protocol.entities.Diagnosis;
import com.healthserviceapp.areas.protocol.models.bindingModels.AddDiagnosisBindingModel;
import com.healthserviceapp.areas.protocol.models.bindingModels.EditDiagnosisBindingModel;
import com.healthserviceapp.areas.protocol.models.viewModels.BasicDiagnosisViewModel;
import com.healthserviceapp.areas.protocol.repositories.DiagnosisRepository;
import com.healthserviceapp.areas.protocol.services.DiagnosisService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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
    public void addNewDiagnosis(AddDiagnosisBindingModel addDiagnosisBindingModel) {
        Diagnosis diagnosis = this.modelMapper.map(addDiagnosisBindingModel, Diagnosis.class);

        this.diagnosisRepository.save(diagnosis);
    }

    @Override
    public List<BasicDiagnosisViewModel> getAllDiagnoses() {
        List<Diagnosis> diagnoses = this.diagnosisRepository.findAll();
        List<BasicDiagnosisViewModel> diagnosisViewModels = new LinkedList<>();
        for (Diagnosis diagnosis : diagnoses) {
            BasicDiagnosisViewModel basicDiagnosisViewModel = this.modelMapper.map(diagnosis, BasicDiagnosisViewModel.class);
            diagnosisViewModels.add(basicDiagnosisViewModel);
        }
        return diagnosisViewModels;
    }

    @Override
    public void deleteDiagnosisById(Long id) {
        this.diagnosisRepository.delete(id);
    }

    @Override
    public EditDiagnosisBindingModel findDiagnosisById(Long id) {
        Diagnosis diagnosis = this.diagnosisRepository.findOne(id);
        EditDiagnosisBindingModel editDiagnosisBindingModel = this.modelMapper.map(diagnosis, EditDiagnosisBindingModel.class);

        return editDiagnosisBindingModel;
    }

    @Override
    public void saveChanges(EditDiagnosisBindingModel editDiagnosisBindingModel) {
        Diagnosis diagnosis = this.diagnosisRepository.findOne(editDiagnosisBindingModel.getId());
        diagnosis.setDescription(editDiagnosisBindingModel.getDescription());

        this.diagnosisRepository.save(diagnosis);
    }
}
