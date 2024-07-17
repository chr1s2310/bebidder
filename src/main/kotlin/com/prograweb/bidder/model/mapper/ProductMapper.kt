package com.prograweb.bidder.model.mapper

import com.prograweb.bidder.model.entities.CategoryEntity
import com.prograweb.bidder.model.entities.ProductEntity
import com.prograweb.bidder.model.entities.ProductImageEntity
import com.prograweb.bidder.model.mapper.CategoryMapper.toResponse
import com.prograweb.bidder.model.mapper.ProductImageMapper.toResponse
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
            href = this.href,
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
            images = this.images.map { it.toResponse() }
        )
    }

    fun ProductRequest.toEntityUpdated(productEntity: ProductEntity, categoryEntity: CategoryEntity, images: MutableList<ProductImageEntity>) : ProductEntity {
        return ProductEntity(
            id = productEntity.id,
            publicId = productEntity.publicId,
            name = this.name,
            title = this.title,
            description = this.description,
            features = this.features,
            price = this.price,
            href = this.href,
            categoryEntity = categoryEntity,
                images = images
        )
    }
}