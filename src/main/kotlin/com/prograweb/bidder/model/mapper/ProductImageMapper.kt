package com.prograweb.bidder.model.mapper

import com.prograweb.bidder.model.entities.ProductEntity
import com.prograweb.bidder.model.entities.ProductImageEntity
import com.prograweb.bidder.model.request.ProductImageRequest
import com.prograweb.bidder.model.response.ProductImageResponse

object ProductImageMapper {

    fun ProductImageRequest.toEntity(productEntity: ProductEntity) : ProductImageEntity {
        return ProductImageEntity(
            image = this.image,
            productEntity = productEntity
        )
    }

    fun ProductImageEntity.toResponse() : ProductImageResponse {
        return ProductImageResponse(
            id = this.id!!,
            publicId = this.publicId,
            image = this.image
        )
    }
}