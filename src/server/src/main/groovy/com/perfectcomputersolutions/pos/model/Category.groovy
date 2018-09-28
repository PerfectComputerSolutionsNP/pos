package com.perfectcomputersolutions.pos.model

import org.springframework.data.mongodb.core.mapping.Document

@Document
class Category extends ModelEntity {

    String name
}