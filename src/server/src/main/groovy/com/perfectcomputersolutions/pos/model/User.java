package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User extends ModelEntity {

    // TODO - Unique constraint for username, notifier, join with transaction table

    // https://stackoverflow.com/questions/17393812/json-and-java-circular-reference
    // https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion

    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "USERNAME", length = 50, unique = true)
    private String username;

    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "PASSWORD", length = 100)
    private String password;

    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "FIRSTNAME", length = 50)
    private String firstname;

    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "LASTNAME", length = 50)
    private String lastname;

    @NotNull
    @Email
    @Size(min = 4, max = 50)
    @Column(name = "EMAIL", length = 50)
    private String email;

    @NotNull
    @Column(name = "ENABLED")
    private Boolean enabled;

    @NotNull
    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @JsonIgnore
    public List<Authority> getAuthorities() {
        return authorities;
    }

    @JsonProperty
    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}
