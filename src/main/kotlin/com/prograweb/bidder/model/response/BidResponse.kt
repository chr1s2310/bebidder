package com.prograweb.bidder.model.response

import java.util.UUID

class BidResponse (

        val id: Long? = null,

        val publicId: UUID? = null,

        val amount: Int? = 0,

        val userPublicId: UUID? = null,

        val headingUser: String? = null,

        val product: ProductResponse? = null,

        val closed: Boolean? = false,

        val initBidDate: String? = null
)