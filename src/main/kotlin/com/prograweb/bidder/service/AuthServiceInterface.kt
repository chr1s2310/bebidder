package com.prograweb.bidder.service

import com.prograweb.bidder.model.request.UserLoginRequest
import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.AuthResponse

interface AuthServiceInterface {

    fun login(userLoginRequest: UserLoginRequest): AuthResponse
    fun register(user: UserRequest): AuthResponse

}