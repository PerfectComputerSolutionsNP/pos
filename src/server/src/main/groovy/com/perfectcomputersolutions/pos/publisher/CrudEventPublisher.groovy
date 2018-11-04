package com.perfectcomputersolutions.pos.publisher

import com.perfectcomputersolutions.pos.event.DeleteByIdEvent
import com.perfectcomputersolutions.pos.event.DeleteByIdsEvent
import com.perfectcomputersolutions.pos.event.FindAllEvent
import com.perfectcomputersolutions.pos.event.FindByIdEvent
import com.perfectcomputersolutions.pos.event.SaveAllEvent
import com.perfectcomputersolutions.pos.event.SaveEvent
import com.perfectcomputersolutions.pos.event.UpdateEvent
import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.payload.Batch
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

import java.lang.reflect.Type

@Component
class CrudEventPublisher<T extends ModelEntity, ID extends Serializable> extends EventPublisher {

    CrudEventPublisher(ApplicationEventPublisher publisher) {
        super(publisher)
    }

    def findById(ID id, T entity, Type type) {

        publish(new FindByIdEvent(id, entity, type))
    }

    def findAll(Iterable<T> entities, Type type) {

        publish(new FindAllEvent(entities, type))
    }

    def save(T input, T output, Type type) {

        publish(new SaveEvent(input, output, type))
    }

    def saveAll(Batch<T> entities, Type type) {

        publish(new SaveAllEvent(entities, type))
    }

    def update(ID id, T input, T output, Type type) {

        publish(new UpdateEvent(id, input, output, type))
    }

    def deleteById(ID id, T entity, Type type) {

        publish(new DeleteByIdEvent(id, entity, type))
    }

    def deleteByIds(Batch<ID> ids, Type type) {

        publish(new DeleteByIdsEvent(ids, type))
    }
}
