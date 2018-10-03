package com.perfectcomputersolutions.pos.notifier

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

import java.beans.Introspector

// https://www.baeldung.com/spring-email
// https://www.youtube.com/watch?v=9DLX8PMXaw0
// http://dolszewski.com/spring/sending-html-mail-with-spring-boot-and-thymeleaf/
// https://memorynotfound.com/spring-mail-sending-email-thymeleaf-html-template-example/
// https://www.twilio.com/docs/sms/tutorials/server-notifications-java-spring

@Service
class EmailSender {

    private static final Logger log = LoggerFactory.getLogger(EmailSender.class)

    @Autowired JavaMailSender sender
    @Autowired TemplateEngine engine

    String build(TemplateEmail email) {

        log.info("Building email from template: ${email.template}")

        def context = new Context()
        def name    = Introspector.decapitalize(email.object.class.simpleName)

        context.setVariable(name, email.object)

        return engine.process(email.template, context)
    }

    @Async
    void message(String to, String subject, String text) throws MailException {

        log.info("Sending simple email")

        def message = new SimpleMailMessage()

        message.setTo(to)
        message.setSubject(subject)
        message.setText(text)

        sender.send(message)
    }

    @Async
    void message(Email email) throws MailException {

        message(
                email.to,
                email.subject,
                email.text
        )
    }

    @Async
    void send(TemplateEmail email) throws MailException {

        log.info("Sending HTML email")

        MimeMessagePreparator preparator = { msg ->

            def helper = new MimeMessageHelper(msg)

            helper.setTo(email.to)
            helper.setSubject(email.subject)

            def content = build(email)

            helper.setText(content, true)
        }

        sender.send(preparator)
    }
}
