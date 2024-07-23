package com.prograweb.bidder.model.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "product_images")
data class ProductImageEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(nullable = false)
        var publicId: UUID = UUID.randomUUID(),

        @Column(nullable = false, columnDefinition = "TEXT")
        var image: String,

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "product_id", nullable = false)
        var productEntity: ProductEntity

) : AuditModel()
