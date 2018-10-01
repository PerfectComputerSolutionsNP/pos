package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.model.NamedEntity
import com.perfectcomputersolutions.pos.service.CrudService
import com.perfectcomputersolutions.pos.service.NamedEntityService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin

@CrossOrigin
abstract class CrudController<T extends ModelEntity, ID extends Serializable> {

    private static final Logger log = LoggerFactory.getLogger(UnprivilegedCrudController.class)

    static final String ENTITIES  = "entities"
    static final String ENTITY    = "entity"
    static final String MESSAGE   = "message"
    static final String CREATED   = "Entity successfully created"
    static final String UPDATED   = "Entity successfully updated"
    static final String DELETED   = "Entity successfully deleted"
    static final String RETRIEVED = "Entity(ies) successfully retrieved"

    abstract CrudService<T, ID> getService()

    static <E extends ModelEntity, I extends Serializable> ResponseEntity findAll(CrudService<E, I> service) {

        def body = [

                (MESSAGE)   : RETRIEVED,
                (ENTITIES)  : service.findAll()
        ]

        respond(body, HttpStatus.OK)
    }

    static <E extends ModelEntity, I extends Serializable> ResponseEntity findById(I id, CrudService<E, I> service) {

        def body = [

                (MESSAGE) : RETRIEVED,
                (ENTITY)  : service.findById(id)
        ]

        respond(body, HttpStatus.OK)
    }

    static <E extends ModelEntity, I extends Serializable> ResponseEntity save(E entity, CrudService<E, I> service) {

        def body = [

                (MESSAGE) : CREATED,
                (ENTITY)  : service.save(entity)
        ]

        respond(body, HttpStatus.ACCEPTED)
    }

    static <E extends ModelEntity, I extends Serializable> ResponseEntity update(I id, E entity, CrudService<E, I> service) {

        def body = [

                (MESSAGE) : UPDATED,
                (ENTITY)  : service.update(id, entity)
        ]

        respond(body, HttpStatus.ACCEPTED)
    }

    static <E extends ModelEntity, I extends Serializable> ResponseEntity deleteById(I id, CrudService<E, I> service) {

        def body = [

                (MESSAGE) : DELETED,
                (ENTITY)  : service.deleteById(id)
        ]

        respond(body, HttpStatus.ACCEPTED)
    }

    static <E extends NamedEntity, I extends Serializable> ResponseEntity findByName(String name, NamedEntityService<E, I> service) {

        def body = [

                (MESSAGE) : RETRIEVED,
                (ENTITY)  : service.findByName(name)
        ]

        respond(body, HttpStatus.OK)
    }

    static <E extends NamedEntity, I extends Serializable> ResponseEntity findByNameContaining(String name, NamedEntityService<E, I> service) {

        def body = [

                (MESSAGE) : RETRIEVED,
                (ENTITY)  : service.findByNameContaining(name)
        ]

        respond(body, HttpStatus.OK)
    }

    static respond(Map<String, ?> body, HttpStatus status) {

        log.info(body.get(MESSAGE) as String)

        return new ResponseEntity(body, status)
    }
}
