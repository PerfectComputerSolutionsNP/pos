package com.perfectcomputersolutions.pos.security;

class JwtAuthenticationException extends RuntimeException {

    JwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
