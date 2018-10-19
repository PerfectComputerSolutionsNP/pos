package com.perfectcomputersolutions.pos.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class NamedEntity extends ModelEntity {

    @NotEmpty
    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 255)
    @ApiModelProperty(notes = "Name is a unique string value bewteen 1 and 255 characters inclusive")
    public String name;
}
