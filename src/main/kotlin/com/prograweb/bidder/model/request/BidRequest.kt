package com.prograweb.bidder.model.request

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.util.UUID

class BidRequest (

        val publicId: UUID? = null,

        @field:NotNull(message = "El monto es requerido")
        @field:Positive(message = "El monto debe ser positivo")
        val amount: Int,

        val userPublicId: UUID? = null,

        @field:NotNull(message = "El producto es requerido")
        val productPublicId: UUID,

        val initBidDate: String? = null
)