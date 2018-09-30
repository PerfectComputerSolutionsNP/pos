package com.perfectcomputersolutions.pos.exception

class AuthenticationException extends RuntimeException {

    AuthenticationException(String message, Throwable cause) {
        super(message, cause)
    }
}
