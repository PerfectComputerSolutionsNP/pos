package com.perfectcomputersolutions.pos.interceptor

import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.utility.Violation
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.safety.Whitelist
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class XssInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(XssInterceptor.class)

    static String getBody(HttpServletRequest request) throws IOException {

        def builder = new StringBuilder()
        def reader  = request.getReader()

        if (reader == null)
            return ""

        String line

        while ((line = reader.readLine()) != null)
            builder.append(line)

        return builder.toString()
    }

    static String clean(String body) {

        def settings = new Document.OutputSettings()

        settings.prettyPrint(false)

        return Jsoup.clean(body, "", Whitelist.basic(), settings)
    }

    static void compare(String field, String original, String cleaned) {

        if (original == cleaned)
            return

        def msg       = "Field '${field}' in header or request body contains executable code or forbidden HTML tags. Original: ${original}"
        def violation = new Violation(field, msg, HttpServletRequest.class.simpleName)

        throw new ValidationException(violation)
    }

    static void sanitize(String field, String value) {

        compare(field, value, clean(value))
    }

    static void processBody(HttpServletRequest request) {

        def original = getBody(request)
        def cleaned  = clean(original)

        compare("request-body", original, cleaned)
    }

    static void processHeaders(HttpServletRequest request) {

        def keys = request.headerNames.toSet()

        keys.forEach({key ->

            sanitize(key, request.getHeader(key))
        })

    }

    @Override
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Must restore byte[] to request body because otherwise the
        // stream has already been closed and is in an illegal state
        // after processing the body

        log.info("Checking request for cross-site scripting attack")

        processHeaders(request)
//        processBody(request)

        log.info("No cross-site scripting detected")

        return true
    }
}
