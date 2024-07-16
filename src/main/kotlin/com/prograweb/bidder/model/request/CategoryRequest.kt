package com.prograweb.bidder.model.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class CategoryRequest (

        @field:NotNull(message = "El nombre de la categoría es requerido")
        @field:NotBlank(message = "El nombre de la categoría no puede estar vacío")
        val name: String
)