package com.prograweb.bidder.model.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "products")
data class ProductEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(nullable = false)
        var publicId: UUID = UUID.randomUUID(),

        @Column(nullable = false)
        var name : String,

        @Column(nullable = false)
        var title : String,

        @Column(nullable = false)
        var description : String,

        @Column(nullable = false)
        var features: List<String> = emptyList(),

        @Column(nullable = false)
        var price: Int,

        @Column(nullable = false)
        var href: String,

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "category_id", nullable = false)
        var categoryEntity: CategoryEntity,

        @OneToMany(mappedBy = "productEntity", orphanRemoval = true, cascade = [CascadeType.ALL])
        var images: MutableList<ProductImageEntity> = mutableListOf(),

        @OneToOne(mappedBy = "productEntity")
        var bidEntity: BidEntity? = null

) : AuditModel()
