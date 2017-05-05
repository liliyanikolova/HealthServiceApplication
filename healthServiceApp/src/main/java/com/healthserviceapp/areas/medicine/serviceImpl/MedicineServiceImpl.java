package com.healthserviceapp.areas.medicine.serviceImpl;

import com.healthserviceapp.areas.medicine.entities.Doze;
import com.healthserviceapp.areas.medicine.entities.Medicine;
import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBidingModel;
import com.healthserviceapp.areas.medicine.repositories.DozeRepository;
import com.healthserviceapp.areas.medicine.repositories.MedicineRepository;
import com.healthserviceapp.areas.medicine.services.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.SortedSet;

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

}
