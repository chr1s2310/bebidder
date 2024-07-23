package com.prograweb.bidder.repository

import com.prograweb.bidder.model.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findByPublicId(publicId: UUID): UserEntity?

    fun findByEmail(email: String): UserEntity?

    fun findByActive(active: Boolean): List<UserEntity>

    fun findBySuscriptionsPublicId(bidPublicId: UUID): List<UserEntity>

    fun findByUsername(username: String): UserEntity?

}