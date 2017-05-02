package com.healthserviceapp.areas.users.services;

import com.healthserviceapp.areas.users.entities.Role;

public interface RoleService {

    Role getDoctorRole();

    Role getAdminRole();
}