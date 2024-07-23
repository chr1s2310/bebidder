package com.prograweb.bidder.service

import com.prograweb.bidder.model.request.CategoryRequest
import com.prograweb.bidder.model.response.CategoryResponse
import java.util.UUID

interface CategoryServiceInterface {

    fun getAll(): List<CategoryResponse>

    fun getAllDropDown(): List<CategoryResponse>

    fun getCategory(publicId: UUID): CategoryResponse

    fun saveCategory(categoryRequest: CategoryRequest): CategoryResponse

    fun deleteCategory(publicId: UUID)

    fun updateCategory(publicId: UUID, categoryRequest: CategoryRequest): CategoryResponse
}