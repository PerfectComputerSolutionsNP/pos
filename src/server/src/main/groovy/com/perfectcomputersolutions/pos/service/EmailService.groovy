package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.factory.EmailFactory
import com.perfectcomputersolutions.pos.model.Email
import com.perfectcomputersolutions.pos.repository.EmailRepository
import com.perfectcomputersolutions.pos.payload.IdBatch
import com.perfectcomputersolutions.pos.payload.SimpleMessage
import com.perfectcomputersolutions.pos.utility.Utility
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

// https://www.baeldung.com/spring-email
// https://www.youtube.com/watch?v=9DLX8PMXaw0
// http://dolszewski.com/spring/sending-html-mail-with-spring-boot-and-thymeleaf/
// https://memorynotfound.com/spring-mail-sending-email-thymeleaf-html-template-example/
// https://www.twilio.com/docs/sms/tutorials/server-notifications-java-spring

@Service
class EmailService {

    // TODO - Put a limit on how many emails can be sent per time frame to avoid potential spam if hacked

    private static final Logger log = LoggerFactory.getLogger(EmailService.class)

    @Autowired JavaMailSender  sender
    @Autowired EmailRepository repository
    @Autowired EmailFactory    factory

    def findAll(int page, int size) {

        Optional<Boolean> sorted  = Optional.of(true)
        Optional<String> property = Optional.of("created")

        CrudService.findAll(repository, page, size, sorted, property)
    }

    def findById(Long id) {

        CrudService.findById(id, repository)
    }

    def deleteById(Long id) {

        CrudService.deleteById(id, repository)
    }

    def deleteByIds(IdBatch<Long> ids) {

        CrudService.deleteByIds(ids, repository)
    }

    private void sendAsync(Email email) {

        MimeMessagePreparator preparator = { msg ->

            def helper = new MimeMessageHelper(msg)

            helper.setTo(email.to)
            helper.setSubject(email.subject)
            helper.setText(email.text, true)
        }

        // TODO - Try, catch?

        sender.send(preparator)

        CrudService.save(email, repository)
    }

    @Async
    void send(Email email) {

        log.info("Sending email to: ${email.to}")

        Utility.validate(email)

        sendAsync(email)

        log.info("Successfully sent email to ${email.to}")
    }

    @Async
    void send(SimpleMessage message) {

        def email = factory.getEmail(
                message.to,
                message.subject,
                message.text
        )

        send(email)
    }
}
