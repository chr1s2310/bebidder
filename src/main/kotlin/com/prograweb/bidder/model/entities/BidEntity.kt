package com.prograweb.bidder.model.entities

import jakarta.persistence.*
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

        @Column
        var lastUserBid: String? = null,

        @OneToOne
        @JoinColumn(name = "product_id", referencedColumnName = "id")
        var productEntity: ProductEntity,

        @Column(nullable = false)
        var closed: Boolean = false

) : AuditModel()