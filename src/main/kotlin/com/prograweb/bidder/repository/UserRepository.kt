package com.prograweb.bidder.repository

import com.prograweb.bidder.model.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findByid(id: Long): UserEntity?
    fun findByEmail(email: String): UserEntity?

    //fun existsByEmail(email: String): Boolean?

    //fun existsByProviderId(providerId: String): Boolean?

    //fun findByProviderId(providerId: String): UserEntity?

}