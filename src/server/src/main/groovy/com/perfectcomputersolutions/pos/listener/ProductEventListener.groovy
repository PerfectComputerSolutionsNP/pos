package com.perfectcomputersolutions.pos.listener

import com.perfectcomputersolutions.pos.annotation.AsyncEventListener
import com.perfectcomputersolutions.pos.event.DeleteByIdEvent
import com.perfectcomputersolutions.pos.event.DeleteByIdsEvent
import com.perfectcomputersolutions.pos.model.Product
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProductEventListener {

    private Logger log = LoggerFactory.getLogger(this.class)

    @AsyncEventListener
    def deleteById(DeleteByIdEvent<Product> event) {

        log.info("DELETING A PRODUCT")
    }

    @AsyncEventListener
    def deleteByIds(DeleteByIdsEvent<Product> event) {

        log.info("DELETING SEVERAL PRODUCTS")
    }
}
