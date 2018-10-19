package com.perfectcomputersolutions.pos.exception

final class CaughtException extends RuntimeException {

    CaughtException(String message, Throwable cause) {
        super(message, cause)
    }
}
