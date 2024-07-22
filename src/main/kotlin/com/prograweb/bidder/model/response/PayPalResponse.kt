package com.prograweb.bidder.model.response

import com.paypal.api.payments.Payment

class PayPalResponse (

    val status: String,
    val message: String,
    val approvalUrl: String? ,
    val paymentId: String?,
    val orderId: String
)