package com.healthserviceapp.areas.medicine.serviceImpl;

import com.healthserviceapp.areas.medicine.entities.Doze;
import com.healthserviceapp.areas.medicine.entities.Medicine;
import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBidingModel;
import com.healthserviceapp.areas.medicine.models.bindingModels.EditMedicineBidingModel;
import com.healthserviceapp.areas.medicine.models.viewModels.BasicMedicineViewModel;
import com.healthserviceapp.areas.medicine.repositories.DozeRepository;
import com.healthserviceapp.areas.medicine.repositories.MedicineRepository;
import com.healthserviceapp.areas.medicine.services.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MedicineServiceImpl implements MedicineService{

    private MedicineRepository medicineRepository;

    private DozeRepository dozeRepository;

    private ModelMapper modelMapper;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, DozeRepository dozeRepository, ModelMapper modelMapper) {
        this.medicineRepository = medicineRepository;
        this.dozeRepository = dozeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean doesCodeExist(String code) {
        Medicine medicine = this.medicineRepository.findByCode(code);
        if (medicine != null){
            return true;
        }

        return false;
    }

    @Override
    public void addNewMedicine(AddMedicineBidingModel addMedicineBidingModel) {
        Medicine medicine = new Medicine();
        medicine.setCode(addMedicineBidingModel.getCode());
        medicine.setName(addMedicineBidingModel.getName());

        this.medicineRepository.save(medicine);

        String measurement = addMedicineBidingModel.getMeasurement();
        Integer[] dozeQuantities = addMedicineBidingModel.getDozes();
        LinkedHashSet<Doze> dozes = new LinkedHashSet();
        for (Integer dozeQuantity : dozeQuantities) {
            Doze doze = new Doze();
            doze.setQuantity(dozeQuantity);
            doze.setMeasurement(measurement);
            doze.setMedicine(medicine);
            this.dozeRepository.save(doze);
            dozes.add(doze);
        }

//        medicine.setDozes(dozes);

//        this.medicineRepository.save(medicine);
    }

    @Override
    public List<BasicMedicineViewModel> getAllMedicines() {
        List<Medicine> medicines = this.medicineRepository.findAll();
        List<BasicMedicineViewModel> basicMedicineViewModels = new LinkedList<>();
        for (Medicine medicine : medicines) {
            BasicMedicineViewModel basicMedicineViewModel = this.modelMapper.map(medicine, BasicMedicineViewModel.class);
            basicMedicineViewModels.add(basicMedicineViewModel);
        }

        return basicMedicineViewModels;
    }

    @Override
    public void deleteMedicineById(Long id) {
        this.medicineRepository.delete(id);
    }

    @Override
    public EditMedicineBidingModel findMedicineById(Long id) {
        Medicine medicine = this.medicineRepository.findOne(id);
        EditMedicineBidingModel editMedicineBidingModel = new EditMedicineBidingModel();
        editMedicineBidingModel.setId(medicine.getId());
        editMedicineBidingModel.setCode(medicine.getCode());
        editMedicineBidingModel.setName(medicine.getName());

        Set<Doze> dozes = medicine.getDozes();
        List<Integer> dozeQuantities = new LinkedList<>();
        for (Doze doze : dozes) {
            dozeQuantities.add(doze.getQuantity());
        }

        editMedicineBidingModel.setDozes(dozeQuantities);

        return editMedicineBidingModel;
    }

}
