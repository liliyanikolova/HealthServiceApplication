package com.healthserviceapp.areas.users.repositories;

import com.healthserviceapp.areas.users.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

    Title findByName(String name);
}
