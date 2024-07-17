package com.prograweb.bidder.service

import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.UserResponse


interface UserServiceInterface {

    fun getAllUsers(): List<UserResponse>
    fun getUser(id: Long): UserResponse
    fun registerUser(user: UserRequest): UserResponse
    fun updateUser(user: UserRequest): UserResponse
    fun deleteUser(id: Long)

    fun login(email: String, password: String): UserResponse? //login de api

    //fun findByEmail(email: String): Optional<SecurityProperties.User>
    //fun existsByEmail(email: String): Boolean?
    //fun existsByProviderId(providerId: String): Boolean?
    //fun findByProviderId(providerId: String): UserResponse?

}