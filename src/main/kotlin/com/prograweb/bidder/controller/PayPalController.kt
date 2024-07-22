package com.prograweb.bidder.controller

import com.paypal.api.payments.Payment
import com.paypal.base.rest.PayPalRESTException
import com.prograweb.bidder.model.entities.TransactionEntity
import com.prograweb.bidder.model.request.PayPalRequest
import com.prograweb.bidder.service.PayPalService
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.view.RedirectView
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Controller
@Slf4j
@RequestMapping("/api/paypal1")
class PayPalController (private val paypalService: PayPalService){

    private val log = LoggerFactory.getLogger(PayPalController::class.java)

    @PostMapping("/createpayment")
    fun createPayment(@Valid @RequestBody request: PayPalRequest
    ): RedirectView {
        try {
            val cancelUrl = "http://localhost:8082/payment/cancel"
            val successUrl = "http://localhost:8082/payment/success"
            println(request)
            val payment: Payment = paypalService.createPayment(request)
            println(payment) //
            for (links in payment.links) {
                if (links.rel == "approval_url") {
                    return RedirectView(links.href)
                }
            }
        } catch (e: PayPalRESTException) {
            log.error("Error occurred:: ", e)
        }

        return RedirectView("/error")
    }

    @GetMapping("/success")
    fun paymentSuccess(
        @RequestParam("paymentId") paymentId: String,
        @RequestParam("PayerID") payerId: String
    ): String {
        println ("paymentId: $paymentId, payerId: $payerId")
        if (paymentId == null || payerId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payment details").toString()
        }
        try {
            val payment: Payment = paypalService.executePayment(paymentId, payerId)
            if (payment.state == "approved") {
                //val transaction = transactionRequest.toEntity(payment)
                ResponseEntity.ok("Payment successful")
            }else {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment not approved")
            }
        } catch (e: PayPalRESTException) {
            log.error("Error occurred:: ", e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment execution failed")
        }
        return "paymentSuccess"
    }

    @GetMapping("/cancel")
    fun paymentCancel(): String {
        return "paymentCancel"
    }

    @GetMapping("/error")
    fun paymentError(): String {
        return "paymentError"
    }
}