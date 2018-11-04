package com.perfectcomputersolutions.pos.event

import org.springframework.core.ResolvableType
import org.springframework.core.ResolvableTypeProvider

import java.lang.reflect.Type

class GenericEvent<T> implements ResolvableTypeProvider {

    // https://spring.io/blog/2015/02/11/better-application-events-in-spring-framework-4-2
    // https://zoltanaltfatter.com/2016/05/11/application-events-with-spring/

    Type type

    GenericEvent(Type type) {

        if (type == null)
            throw new IllegalArgumentException("Type must not be null")

        this.type = type
    }

    @Override
    ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(
                this.getClass(),
                ResolvableType.forType(type)
        )
    }
}
