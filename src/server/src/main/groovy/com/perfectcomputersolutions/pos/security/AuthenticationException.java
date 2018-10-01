package com.perfectcomputersolutions.pos.security;

class AuthenticationException extends RuntimeException {

    AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
