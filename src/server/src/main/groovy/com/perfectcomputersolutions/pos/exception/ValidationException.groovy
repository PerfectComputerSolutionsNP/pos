package com.perfectcomputersolutions.pos.exception

import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.utility.ViolationBatch
import com.perfectcomputersolutions.pos.utility.Violation

/**
 * Exception that is thrown when a validation error occurs
 * for a particular ModelEntity. This exception is typically thrown
 * as a result of a field being invalid when a POST request occurs.
 *
 * @see ModelEntity
 *
 * @param <T> Generic type of exception.
 */
final class ValidationException extends ThrownException {

    final violations = new HashMap<Integer, Set<Violation>>()

    ValidationException(Set<Violation> violations) {

        super("Validation failed")

        if (violations.size() == 0)
            throw new IllegalArgumentException("ViolationException thrown without any actual violations")

        this.violations.put(0, violations)
    }

    ValidationException(ViolationBatch batch) {

        super("Batch validation failed")

        if (batch == null)
            throw new IllegalArgumentException("BatchViolation argument must not be null")

        violations.putAll(batch.violations)
    }

    ValidationException(Violation violation) {

        super(violation.message)

        if (violation == null)
            throw new IllegalArgumentException("Violation object must not be null")

        this.violations.put(0, [violation] as Set<Violation>)
    }
}
