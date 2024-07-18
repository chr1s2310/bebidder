package com.prograweb.bidder.model.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "users")
data class UserEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var publicId: UUID = UUID.randomUUID(),

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var lastname: String,

    @Column(nullable = false)
    var username: String,

    @OneToMany(mappedBy = "userBid", orphanRemoval = true, cascade = [CascadeType.ALL])
    var bids: MutableList<BidEntity> = mutableListOf(),

    @Column(nullable = false)
    var active: Boolean = true

    //@Column(nullable = false)
    //val provider: String?, // "local", "facebook", "google"

    //@Column(nullable = false)
    //val providerId: String? // Getters y setters

): AuditModel()