package com.prograweb.bidder.repository

import com.prograweb.bidder.model.entities.Producto
import org.springframework.data.jpa.repository.JpaRepository

interface ProductoRepository: JpaRepository<Producto, Long> {
    fun findByNombre(nombre: String): Producto
    fun findByTitulo(titulo: String): Producto
}