package com.prograweb.bidder.model.request

import jakarta.validation.constraints.NotNull

class ProductImageRequest(

        @field:NotNull(message = "La imagen no puede ser nula")
        val image: String

)
