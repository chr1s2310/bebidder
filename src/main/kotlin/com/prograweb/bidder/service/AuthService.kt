package com.prograweb.bidder.service
import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.UserResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val userService: UserService
) {

    fun authenticate(email: String, password: String): Authentication {
        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(email, password)
        )
    }

    fun login(email: String, password: String): String {
        val authentication = authenticate(email, password)
        val userDetails = authentication.principal as UserDetails
        return jwtService.generateToken(userDetails.username)
    }

    fun register(user: UserRequest): String {
        val userR = userService.registerUser(user)
        return jwtService.generateToken(userR.email)
    }
}