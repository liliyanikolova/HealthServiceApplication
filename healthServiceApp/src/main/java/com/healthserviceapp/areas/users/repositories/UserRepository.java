package com.healthserviceapp.areas.users.repositories;

import com.healthserviceapp.areas.users.entities.Doctor;
import com.healthserviceapp.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);

    Doctor findById(Long id);
}
