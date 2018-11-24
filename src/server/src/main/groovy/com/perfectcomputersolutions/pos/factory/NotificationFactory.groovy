package com.perfectcomputersolutions.pos.factory

import com.perfectcomputersolutions.pos.model.Notification
import com.perfectcomputersolutions.pos.payload.SimpleMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

import java.beans.Introspector
import java.sql.Timestamp

@Component
class NotificationFactory {

    private static final Logger log = LoggerFactory.getLogger(NotificationFactory.class)

    @Autowired TemplateEngine engine

    private String build(Map<String, ?> variables, String template) {

        def context = new Context()

        variables.forEach({k, v ->

            context.setVariable(k, v)
        })

        return engine.process(template, context)
    }

    Notification getNotification(String to, String subject, String template, Map<String, ?> variables) {

        log.info("Building email for ${to} from template: ${template}")

        def text  = build(variables, template)
        def email = new Notification()

        email.to       = to
        email.subject  = subject
        email.template = template
        email.text     = text

        log.info("Successfully generated email from template: ${template}")

        return email
    }

    def getEmail(
            String to,
            String subject,
            String text) {

        return getNotification(to, subject, "email/simple-message", ["text": text])
    }

    def getEmail(SimpleMessage message) {

        getEmail(
                message.to,
                message.subject,
                message.text
        )
    }

    def getEmail(
            Object object,
            String to,
            String subject,
            String template) {

        def variables = [
                (Introspector.decapitalize(object.class.simpleName)) : object
        ]

        return getNotification(to, subject, template, variables)
    }
}
