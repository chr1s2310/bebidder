package com.prograweb.bidder.model.response

import java.util.UUID

class ProductResponse(

        val id: Long,

        val publicId: UUID,

        val name: String,

        val title: String,

        val description: String,

        val features: List<String>,

        val price: Int,

        val href: String,

        val category: CategoryResponse,

        val images: List<ProductImageResponse>
)
