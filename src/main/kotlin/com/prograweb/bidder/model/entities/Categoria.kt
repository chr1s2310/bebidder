package com.prograweb.bidder.model.entities

import jakarta.persistence.*

@Entity
@Table(name = "categorias")
data class Categoria(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(nullable = false)
        val nombre: String
)
