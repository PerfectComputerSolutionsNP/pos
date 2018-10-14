package com.perfectcomputersolutions.pos.filter

import com.perfectcomputersolutions.pos.exception.ValidationException
import com.perfectcomputersolutions.pos.utility.MultiReadHttpServletRequest
import com.perfectcomputersolutions.pos.utility.Violation
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.safety.Whitelist
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
@Order(1)
class XssFilter implements Filter {

    // https://www.javacodeexamples.com/jsoup-preserve-new-lines-example/799

    private static final Logger log = LoggerFactory.getLogger(XssFilter.class)

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

    static void processBody(MultiReadHttpServletRequest request) {

        def original = request.body
        def cleaned  = clean(original)

        compare("request-body", original, cleaned)
    }

    static void processHeaders(MultiReadHttpServletRequest request) {

        def keys = request.headerNames.toSet()

        keys.forEach({key ->

            sanitize(key, request.getHeader(key))
        })
    }

    @Override
    void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        def req = (HttpServletRequest) request

        log.info("Checking request for cross-site scripting attack")

        def wrapper = new MultiReadHttpServletRequest(req)

        processHeaders(wrapper)
        processBody(wrapper)

        log.info("No cross-site scripting detected, forwarding request to next filter in filter chain")

        chain.doFilter(wrapper, response)
    }

    @Override
    void destroy() {

    }
}
