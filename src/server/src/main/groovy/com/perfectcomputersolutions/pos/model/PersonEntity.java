package com.perfectcomputersolutions.pos.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class PersonEntity extends ModelEntity {

    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "FIRSTNAME", length = 50)
    @ApiModelProperty(notes = "Person's first name should be a string between 4 and 50 characters inclusive.")
    private String firstname;

    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "LASTNAME", length = 50)
    @ApiModelProperty(notes = "Person's last name should be a string between 4 and 50 characters inclusive.")
    private String lastname;

    @NotNull
    @Email
    @Size(min = 4, max = 50)
    @Column(name = "EMAIL", length = 50)
    @ApiModelProperty(notes = "Person's email should be a string between 4 and 50 characters inclusive.")
    private String email;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
