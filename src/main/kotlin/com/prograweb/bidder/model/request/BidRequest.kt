package com.prograweb.bidder.model.request

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.util.UUID

class BidRequest (

        @field:NotNull(message = "El monto es requerido")
        @field:Positive(message = "El monto debe ser positivo")
        val amount: Int,

        val lastUserBid: String,

        @field:NotNull(message = "El producto es requerido")
        val productPublicId: UUID
)