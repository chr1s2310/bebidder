package com.prograweb.bidder.repository

import com.prograweb.bidder.model.entities.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository: JpaRepository<CategoryEntity, Long> {
    fun findByPublicId(publicId: UUID): CategoryEntity?

    fun findByName(name: String): CategoryEntity?

}