package com.perfectcomputersolutions.pos.aspect

import com.perfectcomputersolutions.pos.event.DeleteByIdEvent
import com.perfectcomputersolutions.pos.event.DeleteByIdsEvent
import com.perfectcomputersolutions.pos.event.FindAllEvent
import com.perfectcomputersolutions.pos.event.FindByIdEvent
import com.perfectcomputersolutions.pos.event.SaveAllEvent
import com.perfectcomputersolutions.pos.event.SaveEvent
import com.perfectcomputersolutions.pos.event.UpdateEvent
import com.perfectcomputersolutions.pos.model.ModelEntity
import com.perfectcomputersolutions.pos.payload.Batch
import com.perfectcomputersolutions.pos.utility.EventPublisher
import com.perfectcomputersolutions.pos.service.CrudService
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Aspect
@Component
class CrudEventPublisherAspect {

    // TODO - CASTING MY NOT BE SAFE. Although this method is decoupled, it is rather brittle because lack of generic support
    // and if the location of an function changes, the events will never be published! Is there a more dynamic way to
    // hook in advice for a particular point-cut?

    @Autowired EventPublisher publisher

    @AfterReturning(
            pointcut  = "execution(* com.perfectcomputersolutions.pos.service.CrudService+.findById(Object+))",
            returning = "entity"
    )
    def findById(JoinPoint jp, ModelEntity entity) {

        def target = (CrudService)  jp.target
        def id     = (Serializable) jp.args[0]
        def type   = target.type

        publisher.publish(new FindByIdEvent(id, entity, type))
    }

    @AfterReturning(
            value  = "execution(* com.perfectcomputersolutions.pos.service.CrudService+.findAll(..))",
            returning = "entities"
    )
    def findAll(JoinPoint jp, Object entities) {

        // NOTE - Must cast inside method, not out outside. Idk why. Maybe because of generic type

        def target = (CrudService) jp.target
        def type   = target.type
        entities   = (Iterable<ModelEntity>) entities

        publisher.publish(new FindAllEvent(entities, type))
    }

    @AfterReturning(
            pointcut  = "execution(* com.perfectcomputersolutions.pos.service.CrudService+.save(Object+))",
            returning = "output"
    )
    def save(JoinPoint jp, ModelEntity output) {

        def target = (CrudService) jp.target
        def input  = (ModelEntity) jp.args[0]
        def type   = target.type

        publisher.publish(new SaveEvent(input, output, type))
    }

    @After("execution(void com.perfectcomputersolutions.pos.service.CrudService+.saveAll(..))")
    def saveAll(JoinPoint jp) {

        def target   = (CrudService) jp.target
        def entities = (Batch<ModelEntity>) jp.args[0]
        def type     = target.type

        publisher.publish(new SaveAllEvent(entities, type))
    }

    @AfterReturning(
            pointcut  = "execution(* com.perfectcomputersolutions.pos.service.CrudService+.update(..))",
            returning = "output"
    )
    def update(JoinPoint jp, ModelEntity output) {

        def target = (CrudService) jp.target
        def args   = jp.args
        def id     = (Serializable) args[0]
        def input  = (ModelEntity)  args[1]
        def type   = target.type

        publisher.publish(new UpdateEvent(id, input, output, type))
    }

    @AfterReturning(
            pointcut  = "execution(* com.perfectcomputersolutions.pos.service.CrudService+.deleteById(..))",
            returning = "entity"
    )
    def deleteById(JoinPoint jp, ModelEntity entity) {

        def target = (CrudService)  jp.target
        def id     = (Serializable) jp.args[0]
        def type   = target.type

        publisher.publish(new DeleteByIdEvent(id, entity, type))
    }

    @After("execution(void com.perfectcomputersolutions.pos.service.CrudService+.deleteByIds(..))")
    def deleteByIds(JoinPoint jp) {

        def target = (CrudService)         jp.target
        def ids    = (Batch<Serializable>) jp.args[0]
        def type   = target.type

        publisher.publish(new DeleteByIdsEvent(ids, type))
    }
}
