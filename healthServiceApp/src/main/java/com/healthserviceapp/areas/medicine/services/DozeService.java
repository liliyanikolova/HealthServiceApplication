package com.healthserviceapp.areas.medicine.services;

import com.healthserviceapp.areas.medicine.entities.Doze;

public interface DozeService {

    Doze findByQuantityAndMedicineId(Integer quantity, Long id);
}
