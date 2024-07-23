package com.prograweb.bidder.model.response

import java.util.UUID

class BidHistoryResponse (

    val bidId: UUID,

    val winningUser: String,

    val mount: Int,

    val date: String,

    val product: ProductResponse
)