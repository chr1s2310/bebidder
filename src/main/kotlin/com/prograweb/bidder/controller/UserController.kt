package com.prograweb.bidder.controller

import com.prograweb.bidder.model.request.UserLoginRequest
import com.prograweb.bidder.model.request.UserRequest
import com.prograweb.bidder.model.response.AuthResponse
import com.prograweb.bidder.model.response.UserResponse
import com.prograweb.bidder.service.AuthServiceInterface
import com.prograweb.bidder.service.UserServiceInterface
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/users")
class UserController ( @Autowired private val authService: AuthServiceInterface, @Autowired private val userService: UserServiceInterface) {

    @GetMapping
    fun getAll(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity.ok(userService.getAllUsers())
    }

    @GetMapping("/{publicId}")
    fun getUser(@PathVariable publicId: UUID): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.getUser(publicId))
    }

    @PostMapping
    fun saveUser(@Valid @RequestBody user: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.registerUser(user))
    }

    @PutMapping("/disable/{publicId}")
    fun desactivateUser(@PathVariable publicId: UUID): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.desactivateUser(publicId))
    }

    @PutMapping("/{publicId}")
    fun updateUser(@PathVariable publicId: UUID, @Valid @RequestBody user: UserRequest): ResponseEntity<Pair<UserResponse?, AuthResponse>> {
        return ResponseEntity.ok(userService.updateUser(publicId, user))
    }

    @PostMapping("/login")
    fun loginUser(@Valid @RequestBody user: UserLoginRequest): ResponseEntity<Pair<UserResponse?, AuthResponse>> {
        return ResponseEntity.ok(authService.login(user))
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody user: UserRequest): ResponseEntity<Pair<UserResponse?, AuthResponse>> {
        return ResponseEntity.ok(authService.register(user))
    }
}