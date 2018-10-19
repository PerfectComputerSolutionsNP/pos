package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.Email
import org.springframework.stereotype.Repository

@Repository
interface EmailRepository extends ModelEntityRepository<Email, Long> {
}
