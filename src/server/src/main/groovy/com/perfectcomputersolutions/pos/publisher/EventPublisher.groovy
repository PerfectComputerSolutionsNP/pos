package com.perfectcomputersolutions.pos.publisher

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class EventPublisher {

    private final ApplicationEventPublisher publisher

    EventPublisher(ApplicationEventPublisher publisher) {

        this.publisher = publisher
    }

    def publish(Object event) {

        publisher.publishEvent(event)
    }
}
