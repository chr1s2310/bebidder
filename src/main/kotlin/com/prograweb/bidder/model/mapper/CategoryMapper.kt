package com.prograweb.bidder.model.mapper

import com.prograweb.bidder.model.entities.CategoryEntity
import com.prograweb.bidder.model.mapper.ProductMapper.toResponse
import com.prograweb.bidder.model.request.CategoryRequest
import com.prograweb.bidder.model.response.CategoryResponse

object CategoryMapper {

    fun CategoryRequest.toEntity() : CategoryEntity {
        return CategoryEntity(
            name = this.name
        )
    }

    fun CategoryEntity.toResponse() : CategoryResponse {
        return CategoryResponse(
            id = this.id!!,
            name = this.name,
            products = this.products.map { it.toResponse() },
                publicId = this.publicId
        )
    }

    fun CategoryRequest.toEntityUpdated(categoryEntity: CategoryEntity) : CategoryEntity {
        categoryEntity.name = this.name
        return categoryEntity
    }
}