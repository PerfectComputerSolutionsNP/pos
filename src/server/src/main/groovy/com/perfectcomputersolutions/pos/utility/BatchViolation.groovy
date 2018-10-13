package com.perfectcomputersolutions.pos.utility

class BatchViolation {

    final Map<Integer, Set<Violation>> violations

    BatchViolation(Map<Integer, Set<Violation>> violations) {

        this.violations = new HashMap<>()

        if (violations == null || violations.isEmpty())
            throw new IllegalArgumentException("Violation map must be non-null and not empty")

        violations.forEach({index, set ->

            if (index < 0)
                throw new IllegalArgumentException("Index of violation set must be non-negative")

            else if (set == null || set.empty)
                throw new IllegalArgumentException("Violation set must be non-null and not empty")
        })

        this.violations.putAll(violations)
    }
}
