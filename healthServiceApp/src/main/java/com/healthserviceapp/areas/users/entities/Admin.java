package com.healthserviceapp.areas.users.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin_user")
public class Admin extends User{
}
