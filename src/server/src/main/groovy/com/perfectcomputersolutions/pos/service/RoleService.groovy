package com.perfectcomputersolutions.pos.service

import com.perfectcomputersolutions.pos.model.Authority
import com.perfectcomputersolutions.pos.repository.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleService extends CrudService<Authority, Long> {

    @Autowired RoleRepository repository
}
