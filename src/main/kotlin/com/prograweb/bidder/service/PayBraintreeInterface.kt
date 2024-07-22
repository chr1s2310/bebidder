package com.prograweb.bidder.service

import com.braintreegateway.Result
import com.braintreegateway.Transaction


interface PayBraintreeInterface {

    fun generateClientToken(): String
    fun createTransaction(amount: String, nonce: String) : Result<Transaction>

}