package com.perfectcomputersolutions.pos.controller

import com.perfectcomputersolutions.pos.model.EntityBatch
import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.model.NamedEntity
import com.perfectcomputersolutions.pos.service.CrudService
import com.perfectcomputersolutions.pos.service.NamedEntityService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin

@CrossOrigin
abstract class CrudController<T extends ModelEntity, ID extends Serializable> {

    static final String ENTITY    = "entity"
    static final String MESSAGE   = "message"
    static final String CREATED   = "Entity successfully created"
    static final String UPDATED   = "Entity successfully updated"
    static final String DELETED   = "Entity successfully deleted"
    static final String RETRIEVED = "Entity(ies) successfully retrieved"

    abstract CrudService<T, ID> getService()

    static <E extends ModelEntity, I extends Serializable> ResponseEntity save(
            E                 entity,
            CrudService<E, I> service) {

        def body = [

                (MESSAGE) : CREATED,
                (ENTITY)  : service.save(entity)
        ]

        respond(body, HttpStatus.ACCEPTED)
    }

    static <E extends ModelEntity, I extends Serializable> ResponseEntity saveAll(
            EntityBatch<E>    entities,
            CrudService<E, I> service) {

        service.saveAll(entities)

        def body = [

                (MESSAGE) : CREATED
        ]

        respond(body, HttpStatus.ACCEPTED)
    }

    static <E extends ModelEntity, I extends Serializable> ResponseEntity update(
            I                 id,
            E                 entity,
            CrudService<E, I> service) {

        def body = [

                (MESSAGE) : UPDATED,
                (ENTITY)  : service.update(id, entity)
        ]

        respond(body, HttpStatus.ACCEPTED)
    }

    static <E extends ModelEntity, I extends Serializable> ResponseEntity deleteById(
            I                 id,
            CrudService<E, I> service) {

        def body = [

                (MESSAGE) : DELETED,
                (ENTITY)  : service.deleteById(id)
        ]

        respond(body, HttpStatus.ACCEPTED)
    }

    static <E extends ModelEntity, I extends Serializable> ResponseEntity findAll(
            CrudService<E, I> service,
            int               page,
            int               size,
            Optional<Boolean> sorted,
            Optional<String>  property) {

        def body = service.findAll(page, size, sorted, property)

        respond(body, HttpStatus.OK)
    }

    static <E extends NamedEntity, I extends Serializable> ResponseEntity findByName(
            String                   name,
            NamedEntityService<E, I> service) {

        def body = [

                (MESSAGE) : RETRIEVED,
                (ENTITY)  : service.findByName(name)
        ]

        respond(body, HttpStatus.OK)
    }

    static <E extends NamedEntity, I extends Serializable> ResponseEntity findByNameContaining(
            String                   name,
            int                      page,
            int                      size,
            Optional<Boolean>        sorted,
            Optional<String>         property,
            NamedEntityService<E, I> service) {

        def body = service.findByNameContaining(name, page, size, sorted, property)

        respond(body, HttpStatus.OK)
    }

    static <E extends ModelEntity, I extends Serializable> ResponseEntity findById(
            I                 id,
            CrudService<E, I> service) {

        def body = [

                (MESSAGE) : RETRIEVED,
                (ENTITY)  : service.findById(id)
        ]

        respond(body, HttpStatus.OK)
    }

    static respond(Object body, HttpStatus status) {

        return new ResponseEntity(body, status)
    }
}
