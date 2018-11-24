package com.perfectcomputersolutions.pos.service

import com.google.common.reflect.TypeToken
import com.perfectcomputersolutions.pos.annotation.Legit
import com.perfectcomputersolutions.pos.annotation.NoNullArgs
import com.perfectcomputersolutions.pos.exception.CaughtException
import com.perfectcomputersolutions.pos.exception.MalformedRequestException
import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.payload.Batch
import com.perfectcomputersolutions.pos.repository.ModelEntityRepository
import com.perfectcomputersolutions.pos.utility.Utility
import com.perfectcomputersolutions.pos.utility.Violation
import com.perfectcomputersolutions.pos.model.ModelEntity
import org.hibernate.exception.ConstraintViolationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataAccessException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

/**
 * Generic service that executes CRUD operation for basic
 * {@code ModelEntity} objects.
 *
 * @see ModelEntity
 *
 * @param <T> Generic type that extends {@code ModelEntity}
 * @param <ID> Generic type for entity types.
 */
@Legit
@NoNullArgs
abstract class CrudService<T extends ModelEntity, ID extends Serializable> {

    // https://github.com/google/guava/wiki/ReflectionExplained
    // TODO - https://www.baeldung.com/spring-annotation-bean-pre-processor

    private static final Logger log = LoggerFactory.getLogger(CrudService.class)

    def final typeToken = new TypeToken<T>(this.class) {}
    def final type      = typeToken.type

    abstract ModelEntityRepository<T, ID> getRepository()

    protected boolean existsById(ID id) {

        // We have this requireNotNull() call because this function is called from within
        // this class. Therefore, because of how proxying with beans works, the pointcut will
        // only be applied to the first method that was called. All subsequent internal calls
        // do not have the pointcut applied to them.

        Objects.requireNonNull(id, "ID must not be null")

        log.info("Determining if entity is present with id: ${id}")

        def exists = repository.existsById(id)

        def msg = exists ?
                "There is an entity for id: ${id}" :
                "There is no entity for id: ${id}"

        log.info(msg)

        return exists
    }

    protected requireExistsById(ID id, String message) {

        if (!existsById(id))
            throw new NoSuchEntityException(message)
    }

    long count() {

        log.info("Counting entities")

        long count = repository.count()

        log.info("Found ${count} entities")

        return count
    }

    T findById(ID id) {

        log.info("Finding entity with id: ${id}")

        def optional = repository.findById(id)

        if (optional.present)
            return optional.get()

        else
            throw new NoSuchEntityException("No entity found with the id: ${id}")
    }

    Iterable<T> findAll(
            Integer page,
            Integer size,
            Optional<Boolean> sorted,
            Optional<String> property) {

        log.info("Finding all entities for page ${page} with page size ${size}")

        if (sorted.present && !property.present)
            throw new MalformedRequestException("If the sorted parameter is declared, the property parameter must also be declared")

        def request  = sorted.present && sorted.get() && property.present ?
                new PageRequest(page, size, Sort.Direction.ASC, property.get()) :
                new PageRequest(page, size)

        def entities = repository.findAll(request)

        log.info("Found ${entities.size()} entities")

        return entities
    }

    T save(T entity) {

        log.info("Creating new ${entity.class.simpleName}")

        if (entity.id != null) {

            log.info(entity.toString())

            def violation = new Violation(
                    "id",
                    entity.class.simpleName,
                    "Id must be null as it will be automatically assigned to ${entity.class.simpleName}"
            )

            throw new ValidationException(violation)
        }

        log.info("Persisting new ${entity.class.simpleName} to storage")

        T result

        try {

            result = repository.save(entity)

        } catch (DataAccessException ex) {

            log.error("Failed to persist ${entity.class.simpleName} to storage")

            def msg = "Constraint violation: Make sure the record abides by all unique field constraints"

            throw new CaughtException(msg, ex)
        }

        log.info("Successfully saved ${entity.class.simpleName}")

        return result
    }

    void saveAll(Batch<T> batch) {

        int size = batch.size()

        log.info("Saving ${size} new entities")

        try {

            repository.saveAll(batch)

            log.info("Successfully saved ${size} entities")

        } catch (DataAccessException ex) {

            def msg = "Could not save entities"

            log.error(msg)

            throw new CaughtException(msg, ex)
        }
    }

    T update(ID id, T entity) {

        log.info("Updating ${entity.class.simpleName}")

        if (id != entity.id) {

            def msg = "Path variable id '${id}' does not match entity id '${entity.id}'"

            log.error(msg)

            throw new MalformedRequestException(msg)
        }

        if (entity.id == null || !existsById((ID) entity.id)) {

            def violation = new Violation(

                    "id",
                    entity.class.simpleName,
                    "Id either null or no ${entity.class.simpleName} exists id: ${entity.id}"
            )

            throw new ValidationException(violation)
        }

        log.info("Persisting updated ${entity.class.simpleName} to storage")

        return repository.save(entity)
    }

    T deleteById(ID id) {

        log.info("Deleting entity with id: ${id}")

        T entity = findById(id)

        try {

            repository.deleteById(id)

        } catch (DataIntegrityViolationException ex) {

            def message

            switch (ex.cause) {

                case ConstraintViolationException:
                    message = "A constraint failed. Make sure no child entity's foreign key references this entity"
                    break

                default:
                    message = "Could not execute query, please make sure request is valid by the standard specified in the documentation."
                    break
            }

            throw new CaughtException(message, ex)
        }

        return entity
    }

    void deleteByIds(Batch<ID> ids) {

        int size = ids.size()

        log.info("Deleting ${size} entities by id")

        repository.deleteByIdIn(ids.batch)
    }
}
