package com.prograweb.bidder.service

import com.braintreegateway.*
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
@Slf4j
class PayBraintreeService(
    @Value("\${braintree.merchantId}") private val merchantId: String,
    @Value("\${braintree.publicKey}") private val publicKey: String,
    @Value("\${braintree.privateKey}") private val privateKey: String
): PayBraintreeInterface {


    private val gateway: BraintreeGateway = BraintreeGateway(
        Environment.SANDBOX, // or Environment.PRODUCTION for production
        merchantId,
        publicKey,
        privateKey
    )

    override fun generateClientToken(): String {
        return gateway.clientToken().generate()
    }

    override fun createTransaction(amount: String, nonce: String): Result<Transaction> {
        val request = TransactionRequest()
            .amount(amount.toBigDecimal())
            .paymentMethodNonce(nonce)
            .options()
            .submitForSettlement(true)
            .done()

        return gateway.transaction().sale(request)
    }


}