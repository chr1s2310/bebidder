package com.prograweb.bidder.model.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ContactRequest(

        @field:NotBlank(message = "El correo es requerido")
        @field:NotNull(message = "El correo es requerido")
        val email: String,


        @field:NotBlank(message = "El asunto es requerido")
        @field:NotNull(message = "El asunto es requerido")
        val subject: String
)
