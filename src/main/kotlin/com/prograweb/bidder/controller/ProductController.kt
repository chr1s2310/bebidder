package com.prograweb.bidder.controller

import com.prograweb.bidder.model.request.ProductRequest
import com.prograweb.bidder.model.response.ProductResponse
import com.prograweb.bidder.service.ProductServiceInterface
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("/products")
class ProductController(@Autowired private val productService: ProductServiceInterface) {

    @GetMapping
    fun getAll(): ResponseEntity<List<ProductResponse>> {
        return ResponseEntity.ok(productService.getAll())
    }

    @GetMapping("/{publicId}")
    fun getProductoById(@PathVariable publicId: UUID): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(productService.getProduct(publicId))
    }

    @PostMapping
    fun saveProducto(@Valid @RequestBody product: ProductRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(productService.saveProduct(product))
    }

    @DeleteMapping("/{publicId}")
    fun deleteProducto(@PathVariable publicId: UUID): ResponseEntity<Any> {
        return ResponseEntity.ok(productService.deleteProduct(publicId))
    }

    @PutMapping("/{publicId}")
    fun updateProducto(@PathVariable publicId: UUID, @Valid @RequestBody product: ProductRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(productService.updateProduct(publicId, product))
    }
}