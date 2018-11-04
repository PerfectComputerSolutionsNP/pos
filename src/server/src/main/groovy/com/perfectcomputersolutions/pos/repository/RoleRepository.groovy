package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.Authority
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository extends ModelEntityRepository<Authority, Long> {
}
