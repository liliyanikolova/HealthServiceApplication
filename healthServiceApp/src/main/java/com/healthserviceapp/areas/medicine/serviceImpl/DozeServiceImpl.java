package com.healthserviceapp.areas.medicine.serviceImpl;

import com.healthserviceapp.areas.medicine.entities.Doze;
import com.healthserviceapp.areas.medicine.exceptions.DozeNotFoundException;
import com.healthserviceapp.areas.medicine.repositories.DozeRepository;
import com.healthserviceapp.areas.medicine.services.DozeService;
import org.springframework.beans.factory.annotation.Autowired;

public class DozeServiceImpl implements DozeService{

    private DozeRepository dozeRepository;

    @Autowired
    public DozeServiceImpl(DozeRepository dozeRepository) {
        this.dozeRepository = dozeRepository;
    }

    @Override
    public Doze findByQuantityAndMedicineId(Integer quantity, Long id) {
        Doze doze = this.dozeRepository.findByQuantityAndMedicineId(quantity, id);
        if (doze == null){
            throw new DozeNotFoundException();
        }

        return doze;
    }
}
