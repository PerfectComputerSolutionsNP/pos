package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.exception.MalformedRequestException
import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.model.NamedEntity
import com.perfectcomputersolutions.pos.repository.NamedEntityRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class NamedEntityService<T extends NamedEntity, ID extends Serializable> extends CrudService<T, ID> {

    private static final Logger log = LoggerFactory.getLogger(NamedEntityService.class)

    abstract NamedEntityRepository<T, ID> getRepository()

    static <E extends NamedEntity, I extends Serializable> boolean existsByName(
            String                      name,
            NamedEntityRepository<E, I> repository) {

        log.info("Determining if an entity exists by the name: ${name}")

        boolean exists = repository.existsByName(name)

        def msg = exists ?
                "An entity exists with the name: ${name}" :
                "An entity does not exists with the name ${name}"

        log.info(msg)

        return exists
    }

    static <E extends NamedEntity, I extends Serializable> E findByName(
            String                      name,
            NamedEntityRepository<E, I> repository) throws NoSuchEntityException {

        log.info("Finding entity by name: ${name}")

        if (name == null)
            throw new IllegalArgumentException("Argument name must not be null")

        E entity = repository.findByName(name)

        if (entity == null)
            throw new NoSuchEntityException("No entity found with name: ${name}")

        return entity
    }

    static <E extends NamedEntity, I extends Serializable> Iterable<E> findByNameContaining(
            String                      name,
            int                         page,
            int                         size,
            Optional<Boolean>           sorted,
            Optional<String>            property,
            NamedEntityRepository<E, I> repository) throws MalformedRequestException {

        log.info("Searching for entities containing name: ${name}")

        if (name == null)
            throw new IllegalArgumentException("Argument name must not be null")

        if (sorted.present && !property.present)
            throw new MalformedRequestException("If the sorted parameter is declared, the property parameter must also be declared")

        def entities = repository.findByNameContaining(name, getPageRequest(page, size, sorted, property))

        log.info("Found ${entities.size()} entities corresponding to search: ${name}")

        return entities
    }

    def existsByName(String name) {

        existsByName(name, repository)
    }

    def findByName(String name) throws NoSuchEntityException {

        findByName(name, repository)
    }

    def findByNameContaining(
            String            name,
            int               page,
            int               size,
            Optional<Boolean> sorted,
            Optional<String>  property) throws MalformedRequestException {

        findByNameContaining(name, page, size, sorted, property, repository)
    }
}
