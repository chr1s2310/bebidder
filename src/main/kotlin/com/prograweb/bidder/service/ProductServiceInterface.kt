package com.prograweb.bidder.service

import com.prograweb.bidder.model.request.ProductRequest
import com.prograweb.bidder.model.response.ProductResponse
import java.util.UUID

interface ProductServiceInterface {

    fun getAll(): List<ProductResponse>

    fun getProduct(publicId: UUID): ProductResponse

    fun saveProduct(product: ProductRequest): ProductResponse

    fun deleteProduct(publicId: UUID)

    fun updateProduct(publicId: UUID, product: ProductRequest): ProductResponse
}