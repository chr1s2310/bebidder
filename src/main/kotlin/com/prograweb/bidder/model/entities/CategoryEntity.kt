package com.prograweb.bidder.model.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "categories")
data class CategoryEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(nullable = false)
        var publicId: UUID = UUID.randomUUID(),

        @Column(nullable = false)
        var name: String,

        @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.LAZY)
        var products: MutableList<ProductEntity> = mutableListOf()

) : AuditModel()
