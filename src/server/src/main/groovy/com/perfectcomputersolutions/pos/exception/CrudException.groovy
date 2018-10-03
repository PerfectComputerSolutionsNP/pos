package com.perfectcomputersolutions.pos.exception

class CrudException extends RuntimeException {

    CrudException() {
        super()
    }

    CrudException(String msg) {
        super(msg)
    }

    CrudException(String message, Throwable cause) {
        super(message, cause)
    }

}
