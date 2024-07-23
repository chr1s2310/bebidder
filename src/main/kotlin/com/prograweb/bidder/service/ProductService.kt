package com.prograweb.bidder.service

import com.prograweb.bidder.model.mapper.ProductImageMapper.toEntity
import com.prograweb.bidder.model.mapper.ProductMapper.toEntity
import com.prograweb.bidder.model.mapper.ProductMapper.toEntityUpdated
import com.prograweb.bidder.model.mapper.ProductMapper.toResponse
import com.prograweb.bidder.model.request.ProductRequest
import com.prograweb.bidder.model.response.ProductResponse
import com.prograweb.bidder.repository.CategoryRepository
import com.prograweb.bidder.repository.ProductImageRepository
import com.prograweb.bidder.repository.ProductRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
@Transactional
class ProductService(
        @Autowired private val productRepository: ProductRepository,
        @Autowired private val categoryRepository: CategoryRepository,
        @Autowired private val productImageRepository: ProductImageRepository
): ProductServiceInterface {
    override fun getAll(): List<ProductResponse> {
        try {
            return productRepository.findAll().map { it.toResponse() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getProduct(publicId: UUID): ProductResponse {
        try {
            val product = productRepository.findByPublicId(publicId) ?: throw Exception("Producto no encontrado")
            return product.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun saveProduct(product: ProductRequest): ProductResponse {
        try {
            val categoryEntity = categoryRepository.findByPublicId(product.categoryPublicId) ?: throw Exception("Categoría no encontrada")
            val productEntity = product.toEntity(categoryEntity)
            val images = product.images.map { it.toEntity(productEntity) }.toMutableList()
            productEntity.images = images
            val productSaved = productRepository.save(productEntity)
            return productSaved.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun deleteProduct(publicId: UUID) {
        try {
            val product = productRepository.findByPublicId(publicId) ?: throw Exception("Producto no encontrado")
            productRepository.delete(product)
        } catch (e: Exception) {
            throw e
        }
    }

    override fun updateProduct(publicId: UUID, product: ProductRequest): ProductResponse {
        try {
            val productEntity = productRepository.findByPublicId(publicId) ?: throw Exception("Producto no encontrado")
            val categoryEntity = categoryRepository.findByPublicId(product.categoryPublicId) ?: throw Exception("Categoría no encontrada")
            productEntity.images.clear()
            val images = product.images.map { it.toEntity(productEntity) }.toMutableList()
            productEntity.images.addAll(images)
            val productoEntityUpdated = product.toEntityUpdated(productEntity, categoryEntity)
            val productoSaved = productRepository.save(productoEntityUpdated)
            return productoSaved.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }
}