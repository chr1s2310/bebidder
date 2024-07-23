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

    @Column(columnDefinition = "TEXT")
    var profilePicture: String? = null,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var lastname: String,

    @Column(nullable = false, unique = true)
    var username: String,

    @OneToMany(mappedBy = "winningUser", orphanRemoval = true, cascade = [CascadeType.ALL])
    var bidsWon: MutableList<BidEntity> = mutableListOf(),

    @Column(nullable = false)
    var active: Boolean = true,

    @ManyToMany
    @JoinTable(
            name = "users_bids",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "bid_id")]
    )
    var suscriptions: MutableList<BidEntity> = mutableListOf()

): AuditModel()