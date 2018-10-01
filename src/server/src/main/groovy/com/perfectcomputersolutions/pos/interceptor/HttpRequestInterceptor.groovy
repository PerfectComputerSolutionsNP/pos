package com.perfectcomputersolutions.pos.interceptor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.lang.Nullable
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * HTTP request interceptor that logs the HTTP method
 * being executed and the status code after the call is complete.
 */
class HttpRequestInterceptor extends HandlerInterceptorAdapter {

    // https://stackoverflow.com/questions/46954110/how-to-execute-a-filter-on-specific-request-in-spring-mvc
    // https://www.baeldung.com/spring-model-parameters-with-handler-interceptor

    private static final Logger log = LoggerFactory.getLogger(HttpRequestInterceptor.class)

    @Override
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long start = System.currentTimeMillis()

        log.info("${request.method} request to ${request.requestURL} started at ${start}")

        request.setAttribute("startTime", start)

        return super.preHandle(request, response, handler)
    }

    @Override
    void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                         @Nullable Exception ex) throws Exception {

        long start = (long) request.getAttribute("startTime")
        long end   = System.currentTimeMillis()
        long diff  = end - start

        log.info("${request.method} request to ${request.requestURL} ended at ${end}")
        log.info("Request duration: ${diff} milliseconds")
        log.info("Returning status code ${response.status} to client")

        // TODO - Persist transaction time for statistics by endpoint?

        super.afterCompletion(request, response, handler, ex)
    }
}
