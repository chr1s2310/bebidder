package com.prograweb.bidder.service

import com.prograweb.bidder.model.mapper.UserMapper.toEntity
import com.prograweb.bidder.model.mapper.UserMapper.toEntityUpdated
import com.prograweb.bidder.model.mapper.UserMapper.toResponse
import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.UserResponse
import com.prograweb.bidder.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository, private val passwordEncoder: BCryptPasswordEncoder): UserServiceInterface {

    override fun getAllUsers(): List<UserResponse> {
        try {
            return userRepository.findAll().map { it.toResponse() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getUser(id: Long): UserResponse {
        try {
            val user = userRepository.findByid(id) ?: throw Exception("Usuario no encontrado")
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

    override fun updateUser(user: UserRequest): UserResponse {
        val userEnt = userRepository.findByEmail(user.email) ?: throw Exception("Usuario no encontrado")
        val userEntityUpdate = user.toEntityUpdated(userEnt)
        return userRepository.save(userEntityUpdate).toResponse()
    }

    override fun deleteUser(id: Long) {
        try {
            val user = userRepository.findByid(id) ?: throw Exception("Usuario no encontrado")
            userRepository.delete(user)
        } catch (e: Exception) {
            throw e
        }
    }

    /*override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email) ?: throw UsernameNotFoundException("Usuario no encontrado")
        return org.springframework.security.core.userdetails.User
            .withUsername(user.email)
            .password(user.password)
            .authorities("USER")
            .build()
    }*/

    override fun login(email: String, password: String): UserResponse {
        val user = userRepository.findByEmail(email) ?: throw RuntimeException("Usuario no encontrado")
        if (!passwordEncoder.matches(password, user.password)) {
            throw RuntimeException("Contraseña incorrecta")
        }
        return user.toResponse()
    }

}