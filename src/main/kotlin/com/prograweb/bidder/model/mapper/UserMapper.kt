package com.prograweb.bidder.model.mapper

import com.prograweb.bidder.model.entities.UserEntity
import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.UserResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object UserMapper {
    private val passwordEncoder = BCryptPasswordEncoder()
    fun UserRequest.toEntity() : UserEntity {
        return UserEntity(
            name = this.name,
            lastname = this.lastname,
            username = this.username,
            email = this.email,
            password = passwordEncoder.encode(this.password),
            provider = this.provider,
        )
    }

    fun UserEntity.toResponse() : UserResponse {
        return UserResponse(
            id = this.id!!,
            name = this.name,
            lastname = this.lastname,
            username = this.username,
            email = this.email,
            password = this.password
        )
    }

    fun UserRequest.toEntityUpdated(userEntity: UserEntity) : UserEntity {
        return UserEntity(
            id = userEntity.id,
            name = this.name,
            lastname = this.lastname,
            username = this.username,
            email = this.email,
            password = userEntity.password,
            provider = this.provider
        )
    }
}