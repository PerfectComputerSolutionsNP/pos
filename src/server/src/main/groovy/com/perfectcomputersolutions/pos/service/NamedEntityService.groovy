package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.model.NamedEntity
import com.perfectcomputersolutions.pos.repository.NamedEntityRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class NamedEntityService<T extends NamedEntity, ID extends Serializable> extends CrudService<T, ID> {

    private static final Logger log = LoggerFactory.getLogger(NamedEntityService.class)

    abstract NamedEntityRepository<T, ID> getRepository()

    static <E extends NamedEntity, I extends Serializable> E findByName(
            String name,
            NamedEntityRepository<E, I> repository) throws NoSuchEntityException {

        log.info("Finding entity by name: ${name}")

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
            NamedEntityRepository<E, I> repository) {

        log.info("Searching for entities containing name: ${name}")

        def entities = repository.findByNameContaining(name, getPageRequest(page, size, sorted, property))

        log.info("Found ${entities.size()} entities corresponding to search: ${name}")

        return entities
    }

    def findByName(String name) throws NoSuchEntityException {

        findByName(name, repository)
    }

    def findByNameContaining(
            String            name,
            int               page,
            int               size,
            Optional<Boolean> sorted,
            Optional<String>  property) {

        findByNameContaining(name, page, size, sorted, property, repository)
    }
}
