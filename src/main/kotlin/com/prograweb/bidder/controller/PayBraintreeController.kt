package com.prograweb.bidder.controller

import com.braintreegateway.BraintreeGateway
import com.braintreegateway.TransactionRequest
import com.prograweb.bidder.service.PayBraintreeService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal

@RestController
@RequestMapping("/api/paybrain")
@CrossOrigin
class PayBraintreeController (){

    @Autowired
    lateinit var braintreeService: PayBraintreeService

    @GetMapping("/token")
    fun getToken(): String {
        return braintreeService.generateClientToken()
    }

    @PostMapping("/checkout1")
    fun checkout(@RequestParam amount: String, @RequestParam nonce: String, response: HttpServletResponse): String {
        val result = braintreeService.createTransaction(amount, nonce)
        return if (result.isSuccess) {
            "Payment successful with transaction ID: ${result.target.id}"
        } else {
            "Payment failed: ${result.message}"
        }
    }

    @GetMapping("/client-token")
    fun getClientToken(): Map<String, String> {
        val clientToken = getToken()
        return mapOf("clientToken" to clientToken)
    }

    @PostMapping("/checkout")
    fun checkout(@RequestBody request: CheckoutRequest): Map<String, Any> {

        val result =  braintreeService.createTransaction(request.amount.toString(), request.paymentMethodNonce)

        return if (result.isSuccess) {
            mapOf("success" to true, "transaction" to result.target.id)
        } else {
            mapOf("success" to false, "error" to result.message)
        }
    }



}

data class CheckoutRequest(val amount: BigDecimal, val paymentMethodNonce: String)