package com.prograweb.bidder.controller

import com.prograweb.bidder.model.request.ProductoRequest
import com.prograweb.bidder.model.response.ProductoResponse
import com.prograweb.bidder.service.ProductoServiceInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/productos")
class ProductoController(@Autowired private val productoService: ProductoServiceInterface) {

    //allow all origins to access this endpoint
    @CrossOrigin(origins = ["*"])
    @GetMapping
    fun getAll(): List<ProductoResponse> {
        return productoService.getAll()
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping("/{id}")
    fun getProductoById(@PathVariable id: Long): ProductoResponse {
        return productoService.getProductoById(id)
    }

    @CrossOrigin(origins = ["*"])
    @PostMapping
    fun saveProducto(@RequestBody producto: ProductoRequest): ProductoResponse {
        return productoService.saveProducto(producto)
    }

    @DeleteMapping("/{id}")
    fun deleteProducto(@PathVariable id: Long) {
        productoService.deleteProducto(id)
    }

    @PutMapping("/{id}")
    fun updateProducto(@PathVariable id: Long, @RequestBody producto: ProductoRequest): ProductoResponse {
        return productoService.updateProducto(id, producto)
    }
}