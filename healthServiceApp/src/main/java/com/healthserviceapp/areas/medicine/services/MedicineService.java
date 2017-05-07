package com.healthserviceapp.areas.medicine.services;

import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBindingModel;
import com.healthserviceapp.areas.medicine.models.bindingModels.EditMedicineBindingModel;
import com.healthserviceapp.areas.medicine.models.viewModels.BasicMedicineViewModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MedicineService {

    boolean doesCodeExist(String code);

    void addNewMedicine(AddMedicineBindingModel addMedicineBindingModel);

    List<BasicMedicineViewModel> getAllMedicines();

    void deleteMedicineById(Long id);

    EditMedicineBindingModel findMedicineById(Long id);

    void saveChanges(EditMedicineBindingModel editMedicineBindingModel, HttpServletRequest httpServletRequest);

    Long findMedicineIdByCode(String code);
}
