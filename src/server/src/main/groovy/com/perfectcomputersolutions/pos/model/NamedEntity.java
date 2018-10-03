package com.perfectcomputersolutions.pos.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class NamedEntity extends ModelEntity {

    @NotNull
    @Column(unique = true)
    public String name;
}
