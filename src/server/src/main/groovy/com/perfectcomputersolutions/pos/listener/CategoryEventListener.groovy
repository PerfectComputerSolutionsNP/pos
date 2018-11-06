package com.perfectcomputersolutions.pos.listener

import com.perfectcomputersolutions.pos.annotation.AsyncEventListener
import com.perfectcomputersolutions.pos.event.DeleteByIdEvent
import com.perfectcomputersolutions.pos.event.DeleteByIdsEvent
import com.perfectcomputersolutions.pos.event.FindAllEvent
import com.perfectcomputersolutions.pos.event.FindByIdEvent
import com.perfectcomputersolutions.pos.event.SaveAllEvent
import com.perfectcomputersolutions.pos.event.SaveEvent
import com.perfectcomputersolutions.pos.event.UpdateEvent
import com.perfectcomputersolutions.pos.model.Category
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class CategoryEventListener {

    // TODO - To implement any other EventListener classes, follow the model of this class

    // The parameter type of each function corresponds to the return type of the
    // corresponding function in the CrudService
    // Both AsyncEventListener or EventListener can be used. Async will use
    // the default ThreadExecutor to spawn new threads

    private Logger log = LoggerFactory.getLogger(this.class)

    @EventListener
    def findById(FindByIdEvent<Category> event) {

        log.info("FOUND CATEGORY BY ID: ${event.entity}")
    }

    @AsyncEventListener
    def findAll(FindAllEvent<Category> event) {

        log.info("FOUND SEVERAL CATEGORIES")
    }

    @AsyncEventListener
    def save(SaveEvent<Category> event) {

        log.info("SAVED A CATEGORY: ${event.entity}")
    }

    @AsyncEventListener
    def saveAll(SaveAllEvent<Category> event) {

        log.info("SAVED SEVERAL CATEGORIES")
    }

    @AsyncEventListener
    def update(UpdateEvent<Category> event) {

        log.info("UPDATED A CATEGORY: ${event.output}")
    }

}
