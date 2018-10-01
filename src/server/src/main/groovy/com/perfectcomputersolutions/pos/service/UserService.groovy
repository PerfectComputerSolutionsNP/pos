package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.model.User
import com.perfectcomputersolutions.pos.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class UserService extends CrudService<User, Long> {

    @Autowired UserRepository repository
}
