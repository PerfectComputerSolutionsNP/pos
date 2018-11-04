package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.Notification
import org.springframework.stereotype.Repository

@Repository
interface EmailRepository extends ModelEntityRepository<Notification, Long> {
}
