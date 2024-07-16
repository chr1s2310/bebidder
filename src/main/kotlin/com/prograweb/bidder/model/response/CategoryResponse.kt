package com.prograweb.bidder.model.response

import java.util.UUID

class CategoryResponse (

        val id: Long,

        val publicId: UUID,

        val name: String,

        val products: List<ProductResponse>
)