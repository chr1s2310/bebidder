package com.prograweb.bidder.repository

import com.prograweb.bidder.model.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findByPublicId(publicId: UUID): UserEntity?

    fun findByEmail(email: String): UserEntity?

    fun findByActive(active: Boolean): List<UserEntity>

    //fun existsByEmail(email: String): Boolean?

    //fun existsByProviderId(providerId: String): Boolean?

    //fun findByProviderId(providerId: String): UserEntity?

}