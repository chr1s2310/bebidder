package com.prograweb.bidder.model.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.UUID

class ProductRequest (

        @field:NotNull(message = "El nombre del producto es requerido")
        @field:NotBlank(message = "El nombre del producto no puede estar vacío")
        val name: String,

        @field:NotNull(message = "El título del producto es requerido")
        @field:NotBlank(message = "El título del producto no puede estar vacío")
        val title: String,

        @field:NotNull(message = "La descripción del producto es requerida")
        val description: String,

        @field:NotNull(message = "Las características del producto son requeridas")
        @field:NotEmpty(message = "Debe ingresar por lo menos una característica")
        val features: List<String> = emptyList(),

        @field:NotNull(message = "El precio del producto es requerido")
        val price: Int,

        val href: String,

        @field:NotNull(message = "La categoría del producto es requerida")
        val categoryPublicId: UUID,

        @field:NotEmpty(message = "Debe ingresar por lo menos una imagen")
        val images: List<ProductImageRequest> = emptyList()

)
