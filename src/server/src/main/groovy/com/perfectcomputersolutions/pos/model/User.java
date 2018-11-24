package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User extends PersonEntity {

    // TODO - Do not serialize password, but still require it via Jackson annotations

    @OneToMany(mappedBy = "customer")
    private Set<Transaction> transactions;

    // https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/
    // https://stackoverflow.com/questions/17393812/json-and-java-circular-reference
    // https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion

    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "USERNAME", length = 50, unique = true)
    @ApiModelProperty(notes = "User's unique username should be a string between 4 and 50 characters inclusive.")
    private String username;

    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "PASSWORD", length = 100)
    @ApiModelProperty(notes = "User's password should be a string between 4 and 100 characters inclusive.")
    private String password;

    @NotNull
    @Column(name = "ENABLED")
    @ApiModelProperty(
            notes = "Boolean value to indicate whether or not the user is enabled. If the user is disabled, " +
                    "then the user will not be authenticated and cannot log in until it is reactivated."
    )
    private Boolean enabled;

//    @NotNull
    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(notes = "Timestamp to indicate the last time the user's password was changed (or created).")
    private Date lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    @ApiModelProperty(notes = "List of authorities or privileges assigned to the user.")
    private List<Authority> authorities;

    @JsonIgnore
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

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
