package com.prograweb.bidder.model.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class UserEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val provider: String?, // "local", "facebook", "google"

    @Column(nullable = false)
    val providerId: String? // Getters y setters

): AuditModel()