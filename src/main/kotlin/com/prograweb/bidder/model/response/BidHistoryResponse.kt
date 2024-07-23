package com.prograweb.bidder.model.response

import com.prograweb.bidder.model.entities.ProductEntity
import java.util.Date

class BidHistoryResponse (

    val bidId: String,

    val winningUser: String,

    val mount: Double,

    val date: String,

    val product: ProductEntity
)