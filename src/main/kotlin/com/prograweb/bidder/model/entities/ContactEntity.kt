package com.prograweb.bidder.model.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "contact")
data class ContactEntity (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long? = null,

        @Column(nullable = false)
        var publicId: UUID = UUID.randomUUID(),

        @Column(nullable = false)
        var email: String,

        @Column(nullable = false)
        var subject: String,

        @Column(nullable = false)
        var attended: Boolean = false

): AuditModel()