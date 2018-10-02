package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.exception.Violation
import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.model.NamedEntity
import com.perfectcomputersolutions.pos.repository.NamedEntityRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.CrudRepository

import javax.validation.Validation
import javax.validation.Validator

/**
 * Generic service that executes CRUD operation for basic
 * {@code ModelEntity} objects.
 *
 * @see ModelEntity
 *
 * @param <T> Generic type that extends {@code ModelEntity}
 * @param <ID> Generic type for entity types.
 */
abstract class CrudService<T extends ModelEntity, ID extends Serializable> {

    private static final Logger    log       = LoggerFactory.getLogger(CrudService.class)
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator()

    abstract CrudRepository<T, ID> getRepository()

    // TODO - Implement batch upload, and batch delete by id

    static <E extends ModelEntity, I extends Serializable> boolean existsById(I id, CrudRepository<E, I> repository) {

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

    static <E extends ModelEntity, I extends Serializable> E findById(I id, CrudRepository<E, I> repository) {

        log.info("Finding entity with id: " + id)

        def optional = repository.findById(id)

        if (optional.isPresent())
            return optional.get()

        else
            throw new NoSuchEntityException(id)
    }

    static <E extends ModelEntity, I extends Serializable> Iterable<E> findAll(CrudRepository<E, I> repository) {

        log.info("Finding all entities")

        def entities = repository.findAll()

        log.info("Found " + entities.size() + " entities")

        return entities
    }

    static <E extends ModelEntity, I extends Serializable> E save(E entity, CrudRepository<E, I> repository) {

        log.info("Creating new ${entity.class.simpleName}")

        if (entity.id != null) {

            def violation = new Violation()

            violation.field   = "id"
            violation.entity  = entity.class.simpleName
            violation.message = "Id must be null as it will be automatically assigned to ${entity.class.simpleName}"

            throw new ValidationException(violation)
        }

        validate(entity, false)

        log.info("Persisting new ${entity.class.simpleName} to storage")

        return repository.save(entity)
    }

    static <E extends ModelEntity, I extends Serializable> Iterable<E> saveAll(Iterable<E> entities, CrudRepository<E, I> repository) {

        int size = entities.size()

        log.info("Creating ${size} new entities")

        log.info(entities.toString())

        validate(entities, false)

        log.info("Persisting ${size} new entities")

        return repository.saveAll(entities)
    }

    static <E extends ModelEntity, I extends Serializable> E update(I id, E entity, CrudRepository<E, I> repository) {

        log.info("Updating ${entity.class.simpleName}")

        if (id != entity.id) {

            // Path variable id does not match entity id
        }

        if (entity.id == null || !existsById((I)entity.id, repository)) {

            def violation = new Violation()

            violation.field   = "id"
            violation.entity  = entity.class.simpleName
            violation.message = "Id either null or no ${entity.class.simpleName} exists id: " + entity.id

            throw new ValidationException(violation)
        }

        validate(entity, true)

        log.info("Persisting updated ${entity.class.simpleName} to storage")

        return repository.save(entity)
    }

    static <E extends ModelEntity, I extends Serializable> E deleteById(I id, CrudRepository<E, I> repository) {

        log.info("Deleting entity with id: " + id)

        E entity = findById(id, repository)

        if (entity == null) {

            log.info("")

            throw new NoSuchEntityException(id)
        }

        repository.deleteById(id)

        return entity
    }

    static <E extends NamedEntity, I extends Serializable> E findByName(String name, NamedEntityRepository<E, I> repository) {

        log.info("Finding entity by name: ${name}")

        // TODO - Make sure this works!!!
        // modify NoSuchEntityException to accept a message, etc

        E entity = repository.findByName(name)

        if (entity == null) {

            throw new NoSuchEntityException()
        }

        return entity
    }

    static <E extends NamedEntity, I extends Serializable> Iterable<E> findByNameContaining(String name, NamedEntityRepository<E, I> repository) {

        log.info("Searching for entities containing name: ${name}")

        def entities = repository.findByNameContaining(name)

        log.info("Found ${entities.size()} entities corresponding to search: ${name}")

        return entities
    }

    T findById(ID id) {

        findById(id, repository)
    }


    Iterable<T> findAll() {

        findAll(repository)
    }

    T save(T entity) {

        save(entity, repository)
    }

    Iterable<T> saveAll(Iterable<T> entities) {

        saveAll(entities, repository)
    }

    T update(ID id, T entity) {

        update(id, entity, repository)
    }

    T deleteById(ID id) {

        deleteById(id, repository)
    }

    /**
     * Validates an entity via hibernate validation. If
     * the entity does not pass validation, a {@code ValidationException}
     * is thrown and handled by the global exception handler.
     *
     * @see ValidationException
     *
     * @param entity Entity to validate.
     * @param update Boolean value indicating whether or not the entity
     *        it to be validated as an update or a new entry.
     */
    protected static <E extends ModelEntity> void validate(E entity, boolean update) {

        def violations = validator.validate(entity)

        if (update) {

            // TODO - Check other stuff if necessary
        }

        if (violations.size() != 0)
            throw new ValidationException<T>(violations)
    }

    protected static <E extends ModelEntity> void validate(Iterable<E> entities, boolean update) {

        for (E entity in entities)
            validate(entity, update)

        // TODO - Capture exact violate?
    }
}
