package com.perfectcomputersolutions.pos.config

import com.perfectcomputersolutions.pos.utility.AsyncExceptionHandler
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.CustomizableThreadFactory

import java.util.concurrent.Executor

@EnableAsync
@Configuration
class AsyncConfig implements AsyncConfigurer {

    // https://stackoverflow.com/questions/23732089/how-to-enable-request-scope-in-async-task-executor

    // https://wiki.sei.cmu.edu/confluence/display/java/TPS03-J.+Ensure+that+tasks+executing+in+a+thread+pool+do+not+fail+silently
    // TODO - https://www.baeldung.com/spring-security-async-principal-propagation

    // https://www.baeldung.com/spring-async
    // https://howtodoinjava.com/spring-boot2/enableasync-async-controller/
    // https://howtodoinjava.com/java-concurrency-tutorial/

    @Override
    Executor getAsyncExecutor() {

        def factory  = new CustomizableThreadFactory("jabari-kevyn-")
        def executor = new SimpleAsyncTaskExecutor(factory)

        // TODO - Optimize, make dynamic?
//        executor = new ThreadPoolTaskExecutor()
//        executor.setCorePoolSize(5)
//        executor.setMaxPoolSize(10)
//        executor.setQueueCapacity(10)
//        executor.initialize()

        return executor
    }

    @Override
    AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler()
    }
}
