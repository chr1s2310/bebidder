package com.prograweb.bidder.service

import com.prograweb.bidder.model.entities.Producto
import com.prograweb.bidder.model.mapper.ProductoMapper.toEntity
import com.prograweb.bidder.model.mapper.ProductoMapper.toResponse
import com.prograweb.bidder.model.request.ProductoRequest
import com.prograweb.bidder.model.response.ProductoResponse
import com.prograweb.bidder.repository.ProductoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductoService(@Autowired private val productoRepository: ProductoRepository): ProductoServiceInterface {
    override fun getAll(): List<ProductoResponse> {
        try {
            return productoRepository.findAll().map { it.toResponse() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getProductoById(id: Long): ProductoResponse {
        try {
            val producto: Producto = productoRepository.findById(id).orElseThrow { Exception("Producto no encontrado") }
            return producto.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun saveProducto(producto: ProductoRequest): ProductoResponse {
        try {
            val productoEntity: Producto = producto.toEntity()
            val productoSaved: Producto = productoRepository.save(productoEntity)
            return productoSaved.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun deleteProducto(id: Long) {
        try {
            productoRepository.deleteById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    override fun updateProducto(id: Long, producto: ProductoRequest): ProductoResponse {
        try {
            val productoEntity: Producto = producto.toEntity()
            val productoSaved: Producto = productoRepository.save(productoEntity)
            return productoSaved.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }
}