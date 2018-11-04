package com.perfectcomputersolutions.pos.service

import com.google.common.reflect.TypeToken
import com.perfectcomputersolutions.pos.exception.CaughtException
import com.perfectcomputersolutions.pos.exception.MalformedRequestException
import com.perfectcomputersolutions.pos.exception.NoSuchEntityException
import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.payload.Batch
import com.perfectcomputersolutions.pos.publisher.CrudEventPublisher
import com.perfectcomputersolutions.pos.repository.ModelEntityRepository
import com.perfectcomputersolutions.pos.utility.Violation
import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.utility.Utility
import org.hibernate.exception.ConstraintViolationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
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

    // https://github.com/google/guava/wiki/ReflectionExplained

    // TODO - https://www.baeldung.com/spring-annotation-bean-pre-processor

    private static final Logger log = LoggerFactory.getLogger(CrudService.class)

    @Autowired CrudEventPublisher<T, ID> publisher

    def final typeToken = new TypeToken<T>(this.class) {}
    def final type      = typeToken.type

    abstract ModelEntityRepository<T, ID> getRepository()

    static <E extends ModelEntity, I extends Serializable> E findById(
            I id,
            CrudRepository<E, I> repository) {

        log.info("Finding entity with id: ${id}")

        if (id == null)
            throw new IllegalArgumentException("Id argument must not be null")

        def optional = repository.findById(id)

        if (optional.present)
            return optional.get()

        else
            throw new NoSuchEntityException("Could not find an entity with the id: ${id}")
    }

    static <E extends ModelEntity, I extends Serializable> Iterable<E> findAll(
            PagingAndSortingRepository<E, I> repository,
            int               page,
            int               size,
            Optional<Boolean> sorted,
            Optional<String> property) {

        log.info("Finding all entities for page ${page} with page size ${size}")

        if (sorted.present && !property.present)
            throw new MalformedRequestException("If the sorted parameter is declared, the property parameter must also be declared")

        def request  = getPageRequest(page, size, sorted, property)
        def entities = repository.findAll(request)

        log.info("Found ${entities.size()} entities")

        return entities
    }

    // TODO - Abstract to single method to avoid redundant code!

    static <E extends ModelEntity, I extends Serializable> Iterable<E> findAllSorted(
            PagingAndSortingRepository<E, I> repository,
            int               page,
            int               size,
            Sort.Direction    direction,
            String...         properties) {

        log.info("Finding all entities for ${page} and size ${size} sorted by properties: ${Arrays.toString(properties)}")

        def request  = new PageRequest(page, size, direction, properties)
        def entities = repository.findAll(request)

        log.info("Found ${entities.size()} entities")

        return entities
    }

    static <E extends ModelEntity, I extends Serializable> E save(E entity, CrudRepository<E, I> repository) {

        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null")

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

        } catch (DataAccessException ex) {

            log.error("Failed to persist ${entity.class.simpleName} to storage")

            def msg = "Constraint violation: Make sure the record abides by all unique field constraints"

            throw new CaughtException(msg, ex)
        }

        log.info("Successfully saved ${entity.class.simpleName}")

        return result
    }

    static <E extends ModelEntity, I extends Serializable> void saveAll(Batch<E> batch, CrudRepository<E, I> repository) {

        int size = batch.size()

        log.info("Creating ${size} new entities")

        Utility.validate(batch)

        log.info("Persisting ${size} new entities")

        try {

            repository.saveAll(batch)

            log.info("Successfully saved ${size} entities")

        } catch (DataAccessException ex) {

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

        E entity = findById(id, repository)

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

    static <E extends ModelEntity, I extends Serializable> void deleteByIds(Batch<I> ids, ModelEntityRepository<E, I> repository) {

        if (ids == null || ids.batch.empty)
            throw new IllegalArgumentException("Set of ids must be not be null or empty")

        int size = ids.size()

        log.info("Deleting ${size} entities by id")

        // TODO - Exception handling? Check list of ids for nulls??

        repository.deleteByIdIn(ids.batch)
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

    protected static PageRequest getPageRequest(
            int               page,
            int               size,
            Optional<Boolean> sorted,
            Optional<String> property) {

        return sorted.present && sorted.get() && property.present ?
                new PageRequest(page, size, Sort.Direction.ASC, property.get()) :
                new PageRequest(page, size)
    }

    long count() {

        return count(repository)
    }

    T findById(ID id) {

        T result = findById(id, repository)

        publisher.findById(result, type)

        return result
    }

    Iterable<T> findAll(int page, int size, Optional<Boolean> sorted, Optional<String> property) {

        def results = findAll(repository, page, size, sorted, property)

        publisher.findAll(results, type)

        return results
    }

    Iterable<T> findAllSorted(int page, int size, Sort.Direction direction, String... properties) {

        def results = findAllSorted(repository, page, size, direction, properties)

        publisher.findAll(results, type)

        return results
    }

    T save(T entity) {

        T result = save(entity, repository)

        publisher.save(result, type)

        return result
    }

    void saveAll(Batch<T> entities) {

        saveAll(entities, repository)

        publisher.saveAll(type)
    }

    T update(ID id, T entity) {

        T result = update(id, entity, repository)

        publisher.update(entity, type)

        return result
    }

    T deleteById(ID id) {

        T result = deleteById(id, repository)

        publisher.deleteById(result, type)

        return result
    }

    void deleteByIds(Batch<ID> ids) {

        deleteByIds(ids, repository)

        publisher.deleteByIds(type)
    }
}
