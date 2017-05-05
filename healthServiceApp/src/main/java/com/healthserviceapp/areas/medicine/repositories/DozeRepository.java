package com.healthserviceapp.areas.medicine.repositories;

import com.healthserviceapp.areas.medicine.entities.Doze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DozeRepository extends JpaRepository<Doze, Long>{

    Doze findByQuantity(Integer quantity);
}
