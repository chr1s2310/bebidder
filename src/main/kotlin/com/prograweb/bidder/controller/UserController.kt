package com.prograweb.bidder.controller

import com.prograweb.bidder.model.request.UserLoginRequest
import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.UserResponse
import com.prograweb.bidder.service.AuthService
import com.prograweb.bidder.service.JwtService
import com.prograweb.bidder.service.UserServiceInterface
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController ( @Autowired private val authService: AuthService, @Autowired private val userService: UserServiceInterface) {

    @GetMapping
    fun getAll(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity.ok(userService.getAllUsers())
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.getUser(id))
    }

    @PostMapping
    fun saveUser(@Valid @RequestBody user: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.registerUser(user))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.deleteUser(id))
    }

    @PutMapping
    fun updateUser(@Valid @RequestBody user: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.updateUser(user))
    }

    @PostMapping("/login")
    fun loginUser(@Valid @RequestBody user: UserLoginRequest): ResponseEntity<AuthResponse> {
        val token = authService.login(user.email, user.password)
        return ResponseEntity.ok(AuthResponse(token))
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody user: UserRequest): ResponseEntity<AuthResponse> {

        val token = authService.register(user)

        return ResponseEntity.ok(AuthResponse(token))
    }
}

data class AuthResponse(val token: String)