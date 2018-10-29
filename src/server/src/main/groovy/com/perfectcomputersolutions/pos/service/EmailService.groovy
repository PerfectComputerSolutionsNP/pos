package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.exception.CaughtException
import com.perfectcomputersolutions.pos.factory.EmailFactory
import com.perfectcomputersolutions.pos.model.Email
import com.perfectcomputersolutions.pos.payload.Batch
import com.perfectcomputersolutions.pos.repository.EmailRepository
import com.perfectcomputersolutions.pos.payload.SimpleMessage
import com.perfectcomputersolutions.pos.utility.Utility
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.HandlerExceptionResolver

import javax.servlet.http.HttpServletRequest

// https://www.baeldung.com/spring-email
// https://www.youtube.com/watch?v=9DLX8PMXaw0
// http://dolszewski.com/spring/sending-html-mail-with-spring-boot-and-thymeleaf/
// https://memorynotfound.com/spring-mail-sending-email-thymeleaf-html-template-example/
// https://www.twilio.com/docs/sms/tutorials/server-notifications-java-spring

@Service
class EmailService {

    // TODO - Put a limit on how many emails can be sent per time frame to avoid potential spam if hacked
    // Use a circular buffer or a token bucket
    // https://stackoverflow.com/questions/1407113/throttling-method-calls-to-m-requests-in-n-seconds

    private static final Logger log = LoggerFactory.getLogger(EmailService.class)

    @Autowired JavaMailSender  sender
    @Autowired EmailRepository repository
    @Autowired EmailFactory    factory

    def findAll(int page, int size) {

        CrudService.findAll(
                repository,
                page,
                size,
                Optional.of(true),
                Optional.of("created")
        )
    }

    def findById(Long id) {

        CrudService.findById(id, repository)
    }

    def deleteById(Long id) {

        CrudService.deleteById(id, repository)
    }

    def deleteByIds(Batch<Long> ids) {

        CrudService.deleteByIds(ids, repository)
    }

    @Async
    void deliver(Email email) {

        log.info("Sending email to: ${email.to}")

        Utility.validate(email)

        MimeMessagePreparator preparator = { msg ->

            def helper = new MimeMessageHelper(msg)

            helper.setTo(email.to)
            helper.setSubject(email.subject)
            helper.setText(email.text, true)
        }

        try {

            sender.send(preparator)

            CrudService.save(email, repository)

            log.info("Successfully sent email to ${email.to}")

        } catch (MailException ex) {

            throw new CaughtException("Could not send email", ex)
        }
    }
}
