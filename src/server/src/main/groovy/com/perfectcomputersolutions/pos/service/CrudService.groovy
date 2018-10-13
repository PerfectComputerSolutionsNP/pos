package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.exception.CaughtException
import com.perfectcomputersolutions.pos.exception.MalformedRequestException
import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.utility.Violation
import com.perfectcomputersolutions.pos.model.EntityBatch
import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.notifier.EmailSender
import com.perfectcomputersolutions.pos.utility.Utility
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

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

    private static final Logger log = LoggerFactory.getLogger(CrudService.class)

    @Autowired EmailSender emailer

    abstract PagingAndSortingRepository<T, ID> getRepository()

    static <E extends ModelEntity, I extends Serializable> E findById(
            I id,
            CrudRepository<E, I> repository) throws NoSuchEntityException {

        log.info("Finding entity with id: ${id}")

        if (id == null)
            throw new IllegalArgumentException("Id argument must not be null")

        def optional = repository.findById(id)

        if (optional.present)
            return optional.get()

        else
            throw new NoSuchEntityException(id)
    }

    static <E extends ModelEntity, I extends Serializable> Iterable<E> findAll(
            PagingAndSortingRepository<E, I> repository,
            int               page,
            int               size,
            Optional<Boolean> sorted,
            Optional<String> property) {

        log.info("Finding all entities")

        if (sorted.present && !property.present)
            throw new MalformedRequestException("If the sorted parameter is declared, the property parameter must also be declared")

        def entities = repository.findAll(getPageRequest(page, size, sorted, property))

        log.info("Found ${entities.size()} entities")

        return entities
    }

    static <E extends ModelEntity, I extends Serializable> E save(E entity, CrudRepository<E, I> repository) {

        log.info("Creating new ${entity.class.simpleName}")

        if (entity.id != null) {

            def violation = new Violation(
                    "id",
                    entity.class.simpleName,
                    "Id must be null as it will be automatically assigned to ${entity.class.simpleName}"
            )

            throw new ValidationException(violation)
        }

        Utility.validate(entity)

        log.info("Persisting new ${entity.class.simpleName} to storage")

        E result

        try {

            result = repository.save(entity)

        } catch (DataIntegrityViolationException ex) {

            log.error("Failed to persist ${entity.class.simpleName} to storage")

            def msg = "Constraint violation: Make sure the record abides by all unique field constraints"

            throw new CaughtException(msg, ex)
        }

        log.info("Successfully saved ${entity.class.simpleName}")

        return result
    }

    static <E extends ModelEntity, I extends Serializable> void saveAll(EntityBatch<E> entities, CrudRepository<E, I> repository) {

        int size = entities.size()

        log.info("Creating ${size} new entities")

        Utility.validate(entities.entities)

        log.info("Persisting ${size} new entities")

        try {

            repository.saveAll(entities)

        } catch (DataIntegrityViolationException ex) {

            log.error("")

            def msg = ""

            throw new CaughtException(msg, ex)
        }

        log.info("Successfully saved ${size} entities")
    }

    static <E extends ModelEntity, I extends Serializable> E update(I id, E entity, CrudRepository<E, I> repository) {

        log.info("Updating ${entity.class.simpleName}")

        if (id != entity.id) {

            def violation = new Violation(

                    "id",
                    entity.class.simpleName,
                    "Path variable id ${id} does not match entity id ${entity.id}"
            )

            throw new ValidationException(violation)
        }

        if (entity.id == null || !existsById((I) entity.id, repository)) {

            def violation = new Violation(

                    "id",
                    entity.class.simpleName,
                    "Id either null or no ${entity.class.simpleName} exists id: ${entity.id}"
            )

            throw new ValidationException(violation)
        }

        Utility.validate(entity)

        log.info("Persisting updated ${entity.class.simpleName} to storage")

        return repository.save(entity)
    }

    static <E extends ModelEntity, I extends Serializable> E deleteById(I id, CrudRepository<E, I> repository) {

        log.info("Deleting entity with id: ${id}")

        E entity = findById(id, repository)

        if (entity == null) {

            log.info("No entity found for id: ${id}")

            throw new NoSuchEntityException(id)
        }

        try {

            repository.deleteById(id)

        } catch (Exception ex) {

            log.info("")

        }

        return entity
    }

    protected static <E extends ModelEntity, I extends Serializable> boolean existsById(
            I                    id,
            CrudRepository<E, I> repository) {

        log.info("Determining if entity is present with id: ${id}")

        def exists = repository.existsById(id)

        if (id != null && exists)
            log.info("There is an entity for id: ${id}")

        else
            log.info("There is no entity for id: ${id}")

        return exists
    }

    protected static PageRequest getPageRequest(int page, int size, Optional<Boolean> sorted, Optional<String> property) {

        return sorted.present && sorted.get() && property.present ?
                new PageRequest(page, size, Sort.Direction.ASC, property.get()) :
                new PageRequest(page, size)
    }

    T findById(ID id) {

        findById(id, repository)
    }

    Iterable<T> findAll(int page, int size, Optional<Boolean> sorted, Optional<String> property) {

        findAll(repository, page, size, sorted, property)
    }

    T save(T entity) {

        save(entity, repository)
    }

    void saveAll(EntityBatch<T> entities) {

        saveAll(entities, repository)
    }

    T update(ID id, T entity) {

        update(id, entity, repository)
    }

    T deleteById(ID id) {

        deleteById(id, repository)
    }
}
