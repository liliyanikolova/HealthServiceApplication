package com.healthserviceapp.areas.users.serviceImpl;

import com.healthserviceapp.areas.users.entities.Role;
import com.healthserviceapp.areas.users.repositories.RoleRepository;
import com.healthserviceapp.areas.users.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    public static final String DOCTOR_ROLE = "ROLE_DOCTOR";

    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getDoctorRole() {
        return this.roleRepository.findOneByAuthority(DOCTOR_ROLE);
    }

    @Override
    public Role getAdminRole() {
        return this.roleRepository.findOneByAuthority(ADMIN_ROLE);
    }
}
