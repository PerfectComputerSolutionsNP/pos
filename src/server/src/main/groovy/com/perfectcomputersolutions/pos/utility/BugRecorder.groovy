package com.perfectcomputersolutions.pos.utility

import org.springframework.scheduling.annotation.Async

class BugRecorder {

    static final String CRITICAL = "critical"
    static final String HIGH     = "high"
    static final String MEDIUM   = "medium"
    static final String LOW      = "low"

    @Async
    def static record(Throwable throwable) {

        // TODO - Report bug to administrator
    }
}
