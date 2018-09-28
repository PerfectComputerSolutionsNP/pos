package com.perfectcomputersolutions.pos.crud.validator

/**
 * Reusable validation methods for entity fields.
 */
abstract class Validator<T> {

    static final int MIN_FIELD_LENGTH = 1
    static final int MAX_FIELD_LENGTH = 256

    abstract void valid(T entity)

    def static minLength(String string, int length) {

        return string.length() >= length
    }

    def static maxLength(String string, int length) {

        return string.length() <= length
    }

    def static lengthInBounds(String string, int min, int max) {

        return  minLength(string, min) && maxLength(string, max)
    }

    def static empty(String string) {

        return string == null || string == ""
    }

    def static validLength(String string) {

        return lengthInBounds(string, MIN_FIELD_LENGTH, MAX_FIELD_LENGTH)
    }

}
