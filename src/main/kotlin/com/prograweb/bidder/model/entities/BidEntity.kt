package com.prograweb.bidder.model.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "bids")
data class BidEntity (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(nullable = false)
        var publicId: UUID = UUID.randomUUID(),

        @Column(nullable = false)
        var amount: Int,

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "user_id")
        var winningUser: UserEntity? = null,

        @OneToOne
        @JoinColumn(name = "product_id", referencedColumnName = "id")
        var productEntity: ProductEntity,

        @Column(nullable = false)
        var closed: Boolean = true,

        @Column(nullable = false)
        var initBidDate: LocalDateTime,

        @ManyToMany(mappedBy = "suscriptions")
        var suscriptors: List<UserEntity> = mutableListOf()

) : AuditModel()