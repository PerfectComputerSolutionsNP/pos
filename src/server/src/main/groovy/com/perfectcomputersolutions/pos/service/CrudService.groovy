package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.repository.ModelEntityRepository
import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.exception.Violation
import com.perfectcomputersolutions.pos.model.ModelEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.validation.Validation

abstract class CrudService<T extends ModelEntity, ID extends Serializable> {

    private static final Logger log = LoggerFactory.getLogger(CrudService.class)

    abstract ModelEntityRepository<T, ID> getRepository()

    def validator = Validation.buildDefaultValidatorFactory().getValidator()

    /**
     * Determines if an entity exists by id.
     *
     * @param id Entity's id.
     * @return True if an entity exists with the specified id.
     */
    boolean existsById(ID id) {

        log.info("Determining if entity is present with id: " + id)

        if (id == null)
            throw new NoSuchEntityException()

        def exists = repository.existsById(id)

        if (exists)
            log.info("There is an entity for id: " + id)
        else
            log.info("There is no entity for id: " + id)

        return exists
    }

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
            throw new NoSuchEntityException(id)
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

        if (entity.id != null) {

            def violation = new Violation()

            violation.field   = "id"
            violation.entity  = entity.class.simpleName
            violation.message = "Id must be null as it will be automatically assigned"

            throw new ValidationException(violation)
        }

        validate(entity)

        log.info("Persisting new entity to storage")

        return repository.save(entity)
    }

    /**
     * Updates an entity in database.
     *
     * @param entity Entity to update.
     * @return A copy of the entity.
     */
    T update(T entity) {

        return entity
    }

    /**
     * Deletes an entity by id.
     *
     * @param id Entity's id.
     * @return User that was deleted.
     */
    void deleteById(ID id) {

        log.info("Deleting entity with id: " + id)

        if (!existsById(id))
            throw new NoSuchEntityException(id)

        repository.deleteById(id)
    }

    /**
     * Validates an entity via hibernate validation. If
     * the entity does not pass validation, a {@code ValidationException}
     * is thrown and handled by the global exception handler.
     *
     * @see ValidationException
     *
     * @param entity Entity to validate.
     */
    def validate(T entity) {

        def violations = validator.validate(entity)

        if (violations.size() != 0)
            throw new ValidationException<T>(violations)
    }
}
