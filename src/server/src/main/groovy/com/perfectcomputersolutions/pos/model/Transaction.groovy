package com.perfectcomputersolutions.pos.model

import org.springframework.data.mongodb.core.mapping.Document

/**
 * ModelEntity that represents a transaction made in the POS system.
 */
@Document
class Transaction extends ModelEntity {

    String userId

    String paymentMethodId

    Date date

    List<Product> products

    long paid

    long cost
}