package com.prograweb.bidder.service

import com.prograweb.bidder.model.mapper.UserMapper.toDesactivate
import com.prograweb.bidder.model.mapper.UserMapper.toEntity
import com.prograweb.bidder.model.mapper.UserMapper.toEntityUpdated
import com.prograweb.bidder.model.mapper.UserMapper.toResponse
import com.prograweb.bidder.model.request.UserLoginRequest
import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.UserResponse
import com.prograweb.bidder.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
        @Autowired private val userRepository: UserRepository,
        private val passwordEncoder: BCryptPasswordEncoder
): UserServiceInterface {

    override fun getAllUsers(): List<UserResponse> {
        try {
            return userRepository.findAll().map { it.toResponse() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getAllActiveUsers(): List<UserResponse> {
        try {
            return userRepository.findByActive(true).map { it.toResponse() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getUser(publicId: UUID): UserResponse {
        try {
            val user = userRepository.findByPublicId(publicId) ?: throw Exception("Usuario no encontrado")
            return user.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun registerUser(user: UserRequest): UserResponse {

        val userEntity = user.toEntity()
        if (userRepository.findByEmail(user.email) != null) {
            throw RuntimeException("Email ya registrado")
        }
        return userRepository.save(userEntity).toResponse()
    }

    override fun updateUser(publicId: UUID, user: UserRequest): UserResponse {
        val userEnt = userRepository.findByPublicId(publicId) ?: throw Exception("Usuario no encontrado")
        val userEntityUpdate = user.toEntityUpdated(userEnt)
        return userRepository.save(userEntityUpdate).toResponse()
    }

    override fun desactivateUser(publicId: UUID) {
        try {
            val user = userRepository.findByPublicId(publicId) ?: throw Exception("Usuario no encontrado")
            user.toDesactivate()
            userRepository.save(user)
        } catch (e: Exception) {
            throw e
        }
    }

    override fun login(userLoginRequest: UserLoginRequest): UserResponse {
        val user = userRepository.findByEmail(userLoginRequest.email) ?: throw RuntimeException("Usuario no encontrado")
        if (!passwordEncoder.matches(userLoginRequest.password, user.password)) {
            throw RuntimeException("Contraseña incorrecta")
        }
        return user.toResponse()
    }

}