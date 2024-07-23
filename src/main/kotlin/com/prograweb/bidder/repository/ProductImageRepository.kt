package com.prograweb.bidder.repository

import com.prograweb.bidder.model.entities.ProductImageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageRepository: JpaRepository<ProductImageEntity, Long> {
}