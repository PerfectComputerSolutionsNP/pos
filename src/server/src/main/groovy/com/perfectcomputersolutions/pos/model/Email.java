package com.perfectcomputersolutions.pos.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Email extends ModelEntity {

    @NotNull
    @NotEmpty
    @javax.validation.constraints.Email
    String address;
}
