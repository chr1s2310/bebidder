package com.prograweb.bidder.service

import com.prograweb.bidder.model.request.UserLoginRequest
import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.AuthResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    @Autowired private val jwtService: JwtServiceInterface,
    @Autowired private val userService: UserServiceInterface
): AuthServiceInterface {

    override fun login(userLoginRequest: UserLoginRequest): AuthResponse {
        try {
            val authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(userLoginRequest.email, userLoginRequest.password)
            )
            val userDetails = authentication.principal as UserDetails
            return AuthResponse(jwtService.generateToken(userDetails.username))
        } catch (e: Exception) {
            throw e
        }
    }

    override fun register(user: UserRequest): AuthResponse {
        try {
            val userR = userService.registerUser(user)
            return AuthResponse(jwtService.generateToken(userR.email))
        } catch (e: Exception) {
            throw e
        }
    }
}