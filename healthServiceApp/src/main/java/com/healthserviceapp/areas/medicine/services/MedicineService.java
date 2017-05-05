package com.healthserviceapp.areas.medicine.services;

import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBidingModel;

public interface MedicineService {

    boolean doesCodeExist(String code);

    void addNewMedicine(AddMedicineBidingModel addMedicineBidingModel);
}
