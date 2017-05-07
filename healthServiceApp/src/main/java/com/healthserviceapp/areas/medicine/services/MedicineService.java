package com.healthserviceapp.areas.medicine.services;

import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBidingModel;
import com.healthserviceapp.areas.medicine.models.bindingModels.EditMedicineBidingModel;
import com.healthserviceapp.areas.medicine.models.viewModels.BasicMedicineViewModel;

import java.util.List;

public interface MedicineService {

    boolean doesCodeExist(String code);

    void addNewMedicine(AddMedicineBidingModel addMedicineBidingModel);

    List<BasicMedicineViewModel> getAllMedicines();

    void deleteMedicineById(Long id);

    EditMedicineBidingModel findMedicineById(Long id);

    void saveChanges(EditMedicineBidingModel editMedicineBidingModel);

    Long findMedicineIdByCode(String code);
}
