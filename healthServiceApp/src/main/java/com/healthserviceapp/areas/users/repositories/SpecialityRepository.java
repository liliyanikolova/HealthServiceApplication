package com.healthserviceapp.areas.users.repositories;

import com.healthserviceapp.areas.users.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long>{

    Speciality findOneByName(String name);

    Set<Speciality> findAllByNameIn(String[] names);
}
