package com.prograweb.bidder.service

import com.prograweb.bidder.model.request.UserLoginRequest
import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.UserResponse
import java.util.UUID

interface UserServiceInterface {

    fun getAllActiveUsers(): List<UserResponse>

    fun getAllUsers(): List<UserResponse>

    fun getUser(publicId: UUID): UserResponse

    fun registerUser(user: UserRequest): UserResponse

    fun updateUser(publicId: UUID, user: UserRequest): UserResponse

    fun desactivateUser(publicId: UUID)

    fun login(userLoginRequest: UserLoginRequest): UserResponse?

}