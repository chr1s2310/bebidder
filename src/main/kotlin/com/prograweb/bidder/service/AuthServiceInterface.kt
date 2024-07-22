package com.prograweb.bidder.service

import com.prograweb.bidder.model.request.UserLoginRequest
import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.AuthResponse
import com.prograweb.bidder.model.response.UserResponse

interface AuthServiceInterface {

    fun login(userLoginRequest: UserLoginRequest): Pair<UserResponse?, AuthResponse>
    fun register(user: UserRequest): Pair<UserResponse?, AuthResponse>
    fun logout(): AuthResponse
}