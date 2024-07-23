package com.prograweb.bidder.model.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "transactions")
data class TransactionEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val transactionId: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val orderId: String,

    @Column(nullable = false)
    val userId: UUID,

    @Column(nullable = false)
    var bidId: UUID,

    @Column(nullable = false)
    var paymentId: String,

    @Column(nullable = false)
    var payerId: String,

    @Column(nullable = false)
    var amount: Double,

    @Column(nullable = false)
    var currency: String,

    @Column(nullable = false)
    var state: String,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var paymentMethod: String,

    @Column(nullable = false)
    var dateCreated: String,

)