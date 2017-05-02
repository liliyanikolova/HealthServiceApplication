package com.healthserviceapp.areas.patient.repositories;

import com.healthserviceapp.areas.patient.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
}
