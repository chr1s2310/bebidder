package com.prograweb.bidder.service

import com.paypal.api.payments.Payment
import com.prograweb.bidder.model.request.PayPalRequest

interface PayPalInterface {

    fun createPayment(request: PayPalRequest): Payment
    fun executePayment(paymentId: String, payerId: String): Payment
}