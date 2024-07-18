package com.prograweb.bidder.model.response

import java.util.UUID

class BidResponse (

        val id: Long,

        val publicId: UUID,

        val amount: Int,

        val userBid: String? = null,

        val product: ProductResponse,

        val closed: Boolean,

        val initBidDate: String
)