package com.perfectcomputersolutions.pos.utility

import io.sentry.Sentry
import io.sentry.event.Event
import io.sentry.event.EventBuilder
import io.sentry.event.interfaces.ExceptionInterface
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler

import java.lang.reflect.Method

class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(this.class)

    @Override
    void handleUncaughtException(Throwable throwable, Method method, Object... params) {

        // Get the method that was called, its parameter types, the values
        // and log that to standard error. This give us a little bit of context
        // as to why the exception was thrown. We then send the exception to
        // the remote Sentry server.

        def types = method.parameterTypes
        def sj    = new StringJoiner("\n", "", "")
        def msg   = "Asyncronous call to method ${method.toString()}() threw ${throwable.class.simpleName} on thread: ${Thread.currentThread().id}"

        sj.add(msg)
        sj.add("Parameter values:")

        for (int i = 0; i < params.length; i++)
            sj.add("type:\t${types[i].simpleName}, value\t${params[i]}")

        log.error(sj.toString())
        log.error("Throwable", throwable)

        sendToSentry(msg, throwable)
    }

    private void sendToSentry(String msg, Throwable throwable) {

        // TODO - Send the context (HTTP request that started the thread) if possible to provide more information

        def eventBuilder = new EventBuilder()
                .withMessage(msg)
                .withLevel(Event.Level.ERROR)
                .withLogger(this.class.name)
                .withSentryInterface(new ExceptionInterface(throwable))

        Sentry.capture(eventBuilder)
//        Sentry.clearContext()
    }

}