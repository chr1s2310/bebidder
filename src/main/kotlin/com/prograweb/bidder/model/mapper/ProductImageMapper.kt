package com.prograweb.bidder.model.mapper

import com.prograweb.bidder.model.entities.ProductEntity
import com.prograweb.bidder.model.entities.ProductImageEntity
import com.prograweb.bidder.model.request.ProductImageRequest
import com.prograweb.bidder.model.response.ProductImageResponse
import java.util.Base64

object ProductImageMapper {

    fun ProductImageRequest.toEntity(productEntity: ProductEntity) : ProductImageEntity {
        val imageBytes = Base64.getDecoder().decode(this.image)
        return ProductImageEntity(
            image = imageBytes,
            productEntity = productEntity
        )
    }

    fun ProductImageEntity.toResponse() : ProductImageResponse {
        return ProductImageResponse(
            id = this.id!!,
            publicId = this.publicId,
            image = Base64.getEncoder().encodeToString(this.image)
        )
    }
}