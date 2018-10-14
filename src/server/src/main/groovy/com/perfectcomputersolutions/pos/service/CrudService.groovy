package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.exception.CaughtException
import com.perfectcomputersolutions.pos.exception.MalformedRequestException
import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.repository.ModelEntityRepository
import com.perfectcomputersolutions.pos.utility.IdBatch
import com.perfectcomputersolutions.pos.utility.Violation
import com.perfectcomputersolutions.pos.utility.EntityBatch
import com.perfectcomputersolutions.pos.model.ModelEntity
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

    @Autowired EmailService emailer

    abstract ModelEntityRepository<T, ID> getRepository()

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

            log.info("Successfully saved ${size} entities")

        } catch (DataIntegrityViolationException ex) {

            def msg = "Could not save entities"

            log.error(msg)

            throw new CaughtException(msg, ex)
        }
    }

    static <E extends ModelEntity, I extends Serializable> E update(I id, E entity, CrudRepository<E, I> repository) {

        log.info("Updating ${entity.class.simpleName}")

        if (id != entity.id) {

            def msg = "Path variable id ${id} does not match entity id ${entity.id}"

            log.error(msg)

            throw new MalformedRequestException(msg)
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

        if (id == null)
            throw new IllegalArgumentException("Argument id must not be null")

        E entity = findById(id, repository)

        if (entity == null) {

            log.info("No entity found for id: ${id}")

            throw new NoSuchEntityException(id)
        }

        repository.deleteById(id)

        return entity
    }

    static <E extends ModelEntity, I extends Serializable> void deleteByIds(IdBatch<I> ids, ModelEntityRepository<E, I> repository) {

        if (ids == null || ids.ids.empty)
            throw new IllegalArgumentException("Set of ids must be not be null or empty")

        int size = ids.size()

        log.info("Deleting ${size} entities by id")

        repository.deleteByIdIn(ids.ids)
    }

    static <E extends ModelEntity, I extends Serializable> long count(CrudRepository<E, I> repository) {

        log.info("Counting entities")

        long count = repository.count()

        log.info("Found ${count} entities")

        return count
    }

    protected static <E extends ModelEntity, I extends Serializable> boolean existsById(
            I                    id,
            CrudRepository<E, I> repository) {

        log.info("Determining if entity is present with id: ${id}")

        if (id == null)
            throw new IllegalArgumentException("Argument id must not be null")

        def exists = repository.existsById(id)

        def msg = exists ?
                "There is an entity for id: ${id}" :
                "There is no entity for id: ${id}"

        log.info(msg)

        return exists
    }

    protected static PageRequest getPageRequest(int page, int size, Optional<Boolean> sorted, Optional<String> property) {

        return sorted.present && sorted.get() && property.present ?
                new PageRequest(page, size, Sort.Direction.ASC, property.get()) :
                new PageRequest(page, size)
    }

    long count() {

        return count(repository)
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

    void deleteByIds(IdBatch<ID> ids) {

        deleteByIds(ids, repository)
    }
}
