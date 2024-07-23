package com.prograweb.bidder.model.mapper

import com.prograweb.bidder.model.entities.CategoryEntity
import com.prograweb.bidder.model.entities.ProductEntity
import com.prograweb.bidder.model.entities.ProductImageEntity
import com.prograweb.bidder.model.mapper.ProductImageMapper.toStringResponse
import com.prograweb.bidder.model.request.ProductRequest
import com.prograweb.bidder.model.response.ProductResponse

object ProductMapper {

    fun ProductRequest.toEntity(categoryEntity: CategoryEntity) : ProductEntity {
        return ProductEntity(
            name = this.name,
            title = this.title,
            description = this.description,
            features = this.features,
            price = this.price,
            href = this.href?: "",
            categoryEntity = categoryEntity
        )
    }

    fun ProductEntity.toResponse() : ProductResponse {
        return ProductResponse(
            id = this.id!!,
            publicId = this.publicId,
            name = this.name,
            title = this.title,
            description = this.description,
            features = this.features,
            price = this.price,
            href = this.href,
            category = this.categoryEntity.name,
                categoryPublicId = this.categoryEntity.publicId,
            images = this.images.map { it.toStringResponse() }
        )
    }

    fun ProductRequest.toEntityUpdated(productEntity: ProductEntity, categoryEntity: CategoryEntity) : ProductEntity {
        productEntity.name = this.name
        productEntity.title = this.title
        productEntity.description = this.description
        productEntity.features = this.features
        productEntity.price = this.price
        productEntity.href = this.href?: ""
        productEntity.categoryEntity = categoryEntity
        return productEntity
    }
}