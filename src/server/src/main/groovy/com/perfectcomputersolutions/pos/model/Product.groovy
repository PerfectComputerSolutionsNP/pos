package com.perfectcomputersolutions.pos.model

import org.springframework.data.mongodb.core.mapping.Document

/**
 * ModelEntity
 */
@Document
class Product extends ModelEntity {

    short sku

    String name

    String categoryId
}