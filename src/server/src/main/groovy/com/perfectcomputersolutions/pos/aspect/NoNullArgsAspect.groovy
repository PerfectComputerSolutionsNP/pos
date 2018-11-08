package com.perfectcomputersolutions.pos.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.CodeSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Order(0)
@Component
class NoNullArgsAspect {

    // https://stackoverflow.com/questions/2011089/aspectj-pointcut-for-all-methods-of-a-class-with-specific-annotation

    /*
     * The advice in this class is used in combination with the @NoNullArgs annotation. It intercepts
     * instance methods of any class or particular method annotated with @NoNullArgs and performs
     * a check to ensure that the every argument passed to the method is NOT null. This is useful to ensure
     * that we do not get an unanticipated NullPointerException
     */

    private static final Logger log = LoggerFactory.getLogger(NoNullArgsAspect.class)

    @Before(
            value = "!execution(* *.getMetaClass(..)) && @within(com.perfectcomputersolutions.pos.annotation.NoNullArgs) || @annotation(com.perfectcomputersolutions.pos.annotation.NoNullArgs)"
    )
    void requireNotNull(JoinPoint jp) {

        def method = (CodeSignature) jp.signature
        def types  = method.parameterTypes
        def names  = method.parameterNames
        def args   = jp.args

        log.info("Checking that all arguments passed to ${method.toLongString()} are not null")

        // TODO - This is a VERY BAD Exception, and developers should be notified, change Exception type to FatalException or something

        if (types.length != names.length || types.length != args.length)
            throw new RuntimeException("Types, names, and args array have different lengths. This should NEVER happen")

        for (int i = 0; i < types.length; i++)
            Objects.requireNonNull(args[i], "Parameter ${names[i]} must not be null" as String)
    }
}
