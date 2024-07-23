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
                active = true,
                profilePicture = this.profilePicture
        )
    }

    fun UserEntity.toResponse() : UserResponse {
        return UserResponse(
            id = this.id!!,
            publicId = this.publicId,
            name = this.name,
            lastname = this.lastname,
            username = this.username,
            email = this.email,
            active = this.active
        )
    }

    fun UserRequest.toEntityUpdated(userEntity: UserEntity) : UserEntity {
        userEntity.name = this.name
        userEntity.lastname = this.lastname
        userEntity.username = this.username
        userEntity.email = this.email
        return userEntity
    }

    fun UserEntity.toDesactivate() : UserEntity {
        this.active = false
        return this
    }

    fun UserEntity.toActivate() : UserEntity {
        this.active = true
        return this
    }
}