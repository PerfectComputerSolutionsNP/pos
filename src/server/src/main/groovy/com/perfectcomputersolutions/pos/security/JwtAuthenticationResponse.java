package com.perfectcomputersolutions.pos.security;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    private final JwtUser userDetails;

    public JwtAuthenticationResponse(String token, JwtUser userDetails) {

        this.token       = token;
        this.userDetails = userDetails;
    }

    public String getToken() {
        return this.token;
    }

    public JwtUser getUserDetails() {
        return this.userDetails;
    }
}