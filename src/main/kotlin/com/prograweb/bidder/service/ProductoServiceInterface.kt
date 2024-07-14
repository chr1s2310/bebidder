package com.prograweb.bidder.service

import com.prograweb.bidder.model.request.ProductoRequest
import com.prograweb.bidder.model.response.ProductoResponse

interface ProductoServiceInterface {

    fun getAll(): List<ProductoResponse>

    fun getProductoById(id: Long): ProductoResponse

    fun saveProducto(producto: ProductoRequest): ProductoResponse

    fun deleteProducto(id: Long)

    fun updateProducto(id: Long, producto: ProductoRequest): ProductoResponse
}