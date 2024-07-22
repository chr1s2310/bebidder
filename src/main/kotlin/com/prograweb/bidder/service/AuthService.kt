package com.prograweb.bidder.service

import com.prograweb.bidder.model.request.UserLoginRequest
import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.AuthResponse
import com.prograweb.bidder.model.response.UserResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    @Autowired private val userService: UserServiceInterface
): AuthServiceInterface {

    fun authenticate(email: String, password: String): Authentication {
        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(email, password)
        )
    }

    override fun login(userLoginRequest: UserLoginRequest): Pair<UserResponse?, AuthResponse> {
        val authentication = authenticate(userLoginRequest.email, userLoginRequest.password)
        val userDetails = authentication.principal as UserDetails
        return Pair(userService.login(userLoginRequest), AuthResponse(jwtService.generateToken(userDetails.username)))
    }

    override fun register(user: UserRequest): Pair<UserResponse?, AuthResponse> {
        val userR = userService.registerUser(user)
        return Pair(userR, AuthResponse(jwtService.generateToken(userR.email)))
    }

    override fun logout(): AuthResponse {

        return AuthResponse("")
    }
}