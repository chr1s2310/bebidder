package com.prograweb.bidder.controller

import com.prograweb.bidder.model.request.CategoryRequest
import com.prograweb.bidder.model.response.CategoryResponse
import com.prograweb.bidder.service.CategoryServiceInterface
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/categories")
class CategoryController(@Autowired private val categoryServiceInterface: CategoryServiceInterface) {

    @GetMapping
    fun getAll(): ResponseEntity<List<CategoryResponse>> {
        return ResponseEntity.ok(categoryServiceInterface.getAll())
    }

    @GetMapping("/{publicId}")
    fun getCategoryById(@PathVariable publicId: UUID): ResponseEntity<CategoryResponse> {
        return ResponseEntity.ok(categoryServiceInterface.getCategory(publicId))
    }

    @PostMapping
    fun saveCategory(@Valid @RequestBody categoryRequest: CategoryRequest): ResponseEntity<CategoryResponse> {
        return ResponseEntity.ok(categoryServiceInterface.saveCategory(categoryRequest))
    }

    @DeleteMapping("/{publicId}")
    fun deleteCategory(@PathVariable publicId: UUID): ResponseEntity<Any> {
        return ResponseEntity.ok(categoryServiceInterface.deleteCategory(publicId))
    }

    @PutMapping("/{publicId}")
    fun updateCategory(@PathVariable publicId: UUID, @Valid @RequestBody categoryRequest: CategoryRequest): ResponseEntity<CategoryResponse> {
        return ResponseEntity.ok(categoryServiceInterface.updateCategory(publicId, categoryRequest))
    }
}