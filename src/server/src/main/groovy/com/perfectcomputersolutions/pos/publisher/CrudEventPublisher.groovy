package com.perfectcomputersolutions.pos.publisher

import com.perfectcomputersolutions.pos.event.DeleteByIdEvent
import com.perfectcomputersolutions.pos.event.DeleteByIdsEvent
import com.perfectcomputersolutions.pos.event.FindAllEvent
import com.perfectcomputersolutions.pos.event.FindByIdEvent
import com.perfectcomputersolutions.pos.event.SaveAllEvent
import com.perfectcomputersolutions.pos.event.SaveEvent
import com.perfectcomputersolutions.pos.event.UpdateEvent
import com.perfectcomputersolutions.pos.model.ModelEntity
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

import java.lang.reflect.Type

@Component
class CrudEventPublisher<T extends ModelEntity, ID extends Serializable> extends EventPublisher {

    CrudEventPublisher(ApplicationEventPublisher publisher) {
        super(publisher)
    }

    def findById(T entity, Type type) {

        publish(new FindByIdEvent(entity, type))
    }

    def findAll(Iterable<T> entities, Type type) {

        publish(new FindAllEvent(entities, type))
    }

    def save(T entity, Type type) {

        publish(new SaveEvent(entity, type))
    }

    def saveAll(Type type) {

        publish(new SaveAllEvent(type))
    }

    def update(T entity, Type type) {

        publish(new UpdateEvent(entity, type))
    }

    def deleteById(T entity, Type type) {

        publish(new DeleteByIdEvent(entity, type))
    }

    def deleteByIds(Type type) {

        publish(new DeleteByIdsEvent(type))
    }
}
