package com.prograweb.bidder.service

import com.prograweb.bidder.model.mapper.CategoryMapper.toDropDownResponse
import com.prograweb.bidder.model.mapper.CategoryMapper.toEntity
import com.prograweb.bidder.model.mapper.CategoryMapper.toEntityUpdated
import com.prograweb.bidder.model.mapper.CategoryMapper.toResponse
import com.prograweb.bidder.model.request.CategoryRequest
import com.prograweb.bidder.model.response.CategoryResponse
import com.prograweb.bidder.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CategoryService(@Autowired private val categoryRepository: CategoryRepository): CategoryServiceInterface {
    override fun getAll(): List<CategoryResponse> {
        try {
            return categoryRepository.findAll().map { it.toResponse() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getAllDropDown(): List<CategoryResponse> {
        try {
            return categoryRepository.findAll().map { it.toDropDownResponse() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getCategory(publicId: UUID): CategoryResponse {
        try {
            val category = categoryRepository.findByPublicId(publicId) ?: throw Exception("Categoría no encontrada")
            return category.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }
    @Transactional
    override fun saveCategory(categoryRequest: CategoryRequest): CategoryResponse {
        try {
            val categoryEntity = categoryRequest.toEntity()
            val categorySaved = categoryRepository.save(categoryEntity)
            return categorySaved.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }
    @Transactional
    override fun deleteCategory(publicId: UUID) {
        try {
            val category = categoryRepository.findByPublicId(publicId) ?: throw Exception("Categoría no encontrada")
            categoryRepository.delete(category)
        } catch (e: Exception) {
            throw e
        }
    }
    @Transactional
    override fun updateCategory(publicId: UUID, categoryRequest: CategoryRequest): CategoryResponse {
        try {
            val categoryEntity = categoryRepository.findByPublicId(publicId) ?: throw Exception("Categoría no encontrada")
            val categoryUpdated = categoryRequest.toEntityUpdated(categoryEntity)
            val categorySaved = categoryRepository.save(categoryUpdated)
            return categorySaved.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }
}