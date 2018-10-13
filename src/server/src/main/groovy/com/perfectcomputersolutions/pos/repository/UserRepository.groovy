package com.perfectcomputersolutions.pos.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.perfectcomputersolutions.pos.model.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username)
}