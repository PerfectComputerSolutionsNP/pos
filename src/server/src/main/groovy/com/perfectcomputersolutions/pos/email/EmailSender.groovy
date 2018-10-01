package com.perfectcomputersolutions.pos.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

// https://www.baeldung.com/spring-email
// https://www.youtube.com/watch?v=9DLX8PMXaw0
// http://dolszewski.com/spring/sending-html-mail-with-spring-boot-and-thymeleaf/

@Service
class EmailSender {

    @Autowired JavaMailSender sender

    @Async
    void simpleMessage(String to, String subject, String text) throws MailException {

        def message = new SimpleMailMessage()

        message.setTo(to)
        message.setSubject(subject)
        message.setText(text)

        sender.send(message)
    }
}
