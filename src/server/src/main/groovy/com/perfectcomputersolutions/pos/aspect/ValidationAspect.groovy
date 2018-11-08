package com.perfectcomputersolutions.pos.aspect

import com.perfectcomputersolutions.pos.utility.Utility
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.CodeSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Order(1)
@Component
class ValidationAspect {

    private static final Logger log = LoggerFactory.getLogger(ValidationAspect.class)

    @Before(
            value = "!execution(* *.getMetaClass(..)) && @within(com.perfectcomputersolutions.pos.annotation.Legit) || @annotation(com.perfectcomputersolutions.pos.annotation.Legit)"
    )
    def validate(JoinPoint jp) {

        def method = (CodeSignature) jp.signature
        def args   = jp.args

        log.debug("Checking that all arguments passed to ${method.toLongString()} are valid using bean validation")

        for (Object o : args) {

            if (o instanceof Iterable) {

                o = (Iterable<?>) o

                Utility.validate(o)

            } else {

                Utility.validate(o)
            }
        }

        log.debug("All arguments are valid")
    }
}
