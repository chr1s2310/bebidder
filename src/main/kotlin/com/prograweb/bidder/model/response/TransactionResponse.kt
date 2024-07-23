package com.prograweb.bidder.model.response

import java.util.*

class TransactionResponse (

    var id: UUID?,
    var paymentId: String?,
    var payerId: String?,
    var amount: String?,
    var currency: String?,
    var state: String?,
    var description: String?
)