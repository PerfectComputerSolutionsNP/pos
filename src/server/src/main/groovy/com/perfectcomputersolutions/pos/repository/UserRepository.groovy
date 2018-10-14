package com.perfectcomputersolutions.pos.repository

import com.perfectcomputersolutions.pos.model.User
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends ModelEntityRepository<User, Long> {

    boolean existsByUsername(String username)

    boolean existsByEmail(String email)

    User findByUsername(String username)
}