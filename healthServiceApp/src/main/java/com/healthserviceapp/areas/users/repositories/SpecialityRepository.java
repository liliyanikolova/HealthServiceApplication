package com.healthserviceapp.areas.users.repositories;

import com.healthserviceapp.areas.users.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long>{

    Speciality findOneByName(String name);

    Set<Speciality> findAllByNameIn(String[] names);

    @Query(value = "SELECT s " +
            "FROM Speciality AS s join s.doctors AS d " +
            "WHERE d.id = :doctorId"
    )
    List<Speciality> findByDoctorId(@Param("doctorId") Long doctorId);
}
