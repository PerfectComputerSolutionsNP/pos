package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.email.EmailSender
import com.perfectcomputersolutions.pos.model.User
import com.perfectcomputersolutions.pos.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService extends CrudService<User, Long> {

    @Autowired UserRepository repository
    @Autowired EmailSender    email

    @Override
    User save(User entity) {

        User user = (User) super.save(entity)

        def msg = "Thanks for registering with Perfect Computer Solutions"
        def txt = "Dear ${entity.firstname},\nThank you for registering. Your username is ${entity.username}"

        email.simpleMessage(entity.email, msg, txt)

        return user
    }
}
