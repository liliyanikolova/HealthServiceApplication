package com.healthserviceapp.areas.patient.repositories;

import com.healthserviceapp.areas.patient.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

    Patient findByEgn(String egn);

    @Query(value = "SELECT p " +
            "FROM Patient AS p join p.doctors AS d " +
            "WHERE d.id = :doctorId"
    )
    List<Patient> findLoggedDoctorPatients(@Param("doctorId") Long doctorId);

    @Query(value = "SELECT p " +
            "FROM Patient AS p join p.doctors AS d " +
            "WHERE d.id = :doctorId and p.egn like CONCAT('%', :egn, '%') "
    )
    List<Patient> searchByEgn(@Param("egn") String egn, @Param("doctorId") Long doctorId);
}
