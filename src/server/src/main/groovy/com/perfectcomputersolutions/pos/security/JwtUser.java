package com.perfectcomputersolutions.pos.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    @ApiModelProperty(notes = "User's unique identifier")
    private final Long id;

    @ApiModelProperty(notes = "User's unique username")
    private final String username;

    @ApiModelProperty(notes = "User's first name")
    private final String firstname;

    @ApiModelProperty(notes = "User's last name")
    private final String lastname;

    @ApiModelProperty(notes = "User's password")
    private final String password;

    @ApiModelProperty(notes = "User's email")
    private final String email;

    @ApiModelProperty(notes = "Collection of authorities granted to this user")
    private final Collection<? extends GrantedAuthority> authorities;

    @ApiModelProperty(notes = "Boolean flag indicating whether or not the user is enabled")
    private final boolean enabled;

    @ApiModelProperty(notes = "UTC timestamp indicating the last time the user's password was changed")
    private final Date lastPasswordResetDate;

    public JwtUser(
            Long id,
            String username,
            String firstname,
            String lastname,
            String email,
            String password, Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            Date lastPasswordResetDate) {

        this.id                    = id;
        this.username              = username;
        this.firstname             = firstname;
        this.lastname              = lastname;
        this.email                 = email;
        this.password              = password;
        this.authorities           = authorities;
        this.enabled               = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}