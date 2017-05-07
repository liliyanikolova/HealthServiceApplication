package com.healthserviceapp.areas.protocol.repositories;

import com.healthserviceapp.areas.protocol.entities.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long>{

    Diagnosis findByCode(String code);
}
