package com.healthserviceapp.areas.medicine.repositories;

import com.healthserviceapp.areas.medicine.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>{

    Medicine findByCode(String code);
}
