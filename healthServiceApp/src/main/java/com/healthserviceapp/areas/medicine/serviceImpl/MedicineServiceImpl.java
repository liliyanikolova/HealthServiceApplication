package com.healthserviceapp.areas.medicine.serviceImpl;

import com.healthserviceapp.areas.medicine.entities.Doze;
import com.healthserviceapp.areas.medicine.entities.Medicine;
import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBindingModel;
import com.healthserviceapp.areas.medicine.models.bindingModels.EditMedicineBindingModel;
import com.healthserviceapp.areas.medicine.models.viewModels.BasicMedicineViewModel;
import com.healthserviceapp.areas.medicine.repositories.DozeRepository;
import com.healthserviceapp.areas.medicine.repositories.MedicineRepository;
import com.healthserviceapp.areas.medicine.services.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    public void addNewMedicine(AddMedicineBindingModel addMedicineBindingModel) {
        Medicine medicine = new Medicine();
        medicine.setCode(addMedicineBindingModel.getCode());
        medicine.setName(addMedicineBindingModel.getName());

        this.medicineRepository.save(medicine);

        String measurement = addMedicineBindingModel.getMeasurement();
        Integer[] dozeQuantities = addMedicineBindingModel.getDozes();
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
    public EditMedicineBindingModel findMedicineById(Long id) {
        Medicine medicine = this.medicineRepository.findOne(id);
        EditMedicineBindingModel editMedicineBindingModel = new EditMedicineBindingModel();
        editMedicineBindingModel.setId(medicine.getId());
        editMedicineBindingModel.setCode(medicine.getCode());
        editMedicineBindingModel.setName(medicine.getName());

        List<Doze> dozes = medicine.getDozes();
        String measurement = null;
        Integer[] dozeQuantities = new Integer[dozes.size()];
        for (int i = 0; i < dozeQuantities.length; i++) {
            dozeQuantities[i] = dozes.get(i).getQuantity();
            measurement = dozes.get(i).getMeasurement();
        }

        editMedicineBindingModel.setDozes(dozeQuantities);
        editMedicineBindingModel.setMeasurement(measurement);

        return editMedicineBindingModel;
    }

    @Override
    public void saveChanges(EditMedicineBindingModel editMedicineBindingModel, HttpServletRequest httpServletRequest) {
        Map<String,String[]> formData = httpServletRequest.getParameterMap();
        Medicine medicine = this.medicineRepository.findOne(editMedicineBindingModel.getId());
        medicine.setName(formData.get("name")[0]);

        this.medicineRepository.save(medicine);

        String measurement = formData.get("measurement")[0];
        String[] dozeQuantities = formData.get("dozes[]");
        LinkedHashSet<Doze> dozes = new LinkedHashSet();
        medicine.getDozes().clear();
        for (String dozeQuantity : dozeQuantities) {
            if (dozeQuantity.equals("")){
                continue;
            }

            Doze doze = new Doze();
            doze.setQuantity(Integer.parseInt(dozeQuantity));
            doze.setMeasurement(measurement);
            doze.setMedicine(medicine);
            this.dozeRepository.save(doze);
            dozes.add(doze);
        }
    }

    @Override
    public Long findMedicineIdByCode(String code) {
        Medicine medicine = this.medicineRepository.findByCode(code);
        Long id = medicine.getId();

        return id;
    }

}
