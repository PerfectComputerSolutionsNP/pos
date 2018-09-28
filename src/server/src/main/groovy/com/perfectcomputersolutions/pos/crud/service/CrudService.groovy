package com.perfectcomputersolutions.pos.crud.service

import com.perfectcomputersolutions.pos.crud.validator.Validator
import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.CrudRepository

import java.security.InvalidParameterException

abstract class CrudService<T extends ModelEntity, ID extends Serializable> {

    private static final Logger log = LoggerFactory.getLogger(CrudService.class)

    abstract CrudRepository<T, ID> getRepository()
    abstract Validator<T> getValidator()

    /**
     * Find an entity by id.
     *
     * @param id Entity's id.
     * @return Associated entity.
     */
    T findById(ID id) {

        log.info("Finding entity with id: " + id)

        def optional = repository.findById(id)

        if (optional.isPresent())
            return optional.get()

        else
            throw new InvalidParameterException("No entity with id: " + id)
    }

    /**
     * Returns list of all entities.
     *
     * @return List of all entities.
     */
    Iterable<T> findAll() {

        log.info("Finding all entities")

        def entities = repository.findAll()

        log.info("Found " + entities.size() + " entities")

        return entities
    }

    /**
     * Persists an entity to storage.
     *
     * @param entity Entity to persist.
     * @return A copy of the persisted entity.
     */
    T save(T entity) {

        log.info("Creating new entity")

//        validator.valid(entity)

        log.info("Persisting new entity to storage")

        return repository.save(entity)
    }

    /**
     * Deletes an entity by id.
     *
     * @param id Entity's id.
     * @return User that was deleted.
     */
    void deleteById(ID id) {

        log.info("Deleting entity with id: " + id)

        if (!repository.existsById(id))
            throw new InvalidParameterException("No entity with id: " + id)

        repository.deleteById(id)
    }
}
