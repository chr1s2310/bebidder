package com.prograweb.bidder.repository

import com.prograweb.bidder.model.entities.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductRepository: JpaRepository<ProductEntity, Long> {

    fun findByPublicId(publicId: UUID): ProductEntity?

    fun findByName(nombre: String): ProductEntity?

    fun findByTitle(titulo: String): ProductEntity?

    fun findByCategoryEntityPublicId(publicId: UUID): List<ProductEntity>
}