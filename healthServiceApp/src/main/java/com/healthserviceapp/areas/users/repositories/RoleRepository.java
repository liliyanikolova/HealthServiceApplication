package com.healthserviceapp.areas.users.repositories;

import com.healthserviceapp.areas.users.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findOneByAuthority(String authority);
}
