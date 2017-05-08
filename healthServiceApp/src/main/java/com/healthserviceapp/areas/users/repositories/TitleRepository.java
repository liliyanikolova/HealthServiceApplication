package com.healthserviceapp.areas.users.repositories;

import com.healthserviceapp.areas.users.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

    Title findByName(String name);

    @Query(value = "SELECT t " +
            "FROM Title AS t join t.doctors AS d " +
            "WHERE d.id = :doctorId"
    )
    Title findByDoctorId(@Param("doctorId") Long doctorId);
}
