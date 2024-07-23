package com.prograweb.bidder.controller

import com.paypal.base.rest.PayPalRESTException
import com.prograweb.bidder.model.entities.TransactionEntity
import com.prograweb.bidder.model.request.PayPalRequest
import com.prograweb.bidder.repository.TransactionRepository
import com.prograweb.bidder.service.PayPalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/payments")
class PayPalController (@Autowired private val paypalService: PayPalService, @Autowired private val transactionRepository: TransactionRepository) {

    @PostMapping("/pay")
    fun pay(@RequestBody paymentRequest: PayPalRequest): String {
        try {
            val payment = paypalService!!.createPayment(paymentRequest
            )
            for (links in payment.links) {
                if (links.rel.equals("approval_url")) {
                    return links.href
                }
            }
        } catch (e: PayPalRESTException) {
            e.printStackTrace()
        }
        return "/"
    }

    @GetMapping("/success")
    fun successPay(@RequestParam("paymentId") paymentId: String?, @RequestParam("PayerID") payerId: String?): String {
        println("paymentId: $paymentId, payerId: $payerId")
        return try {
            val payment = paypalService.executePayment(paymentId!!, payerId!!)

            if (payment.state == "approved") {
                //Transacción en la base de datos
                    val transaction = TransactionEntity(
                        paymentId = paymentId,
                        payerId = payerId,
                        bidId = UUID.randomUUID(), // Reemplazar con el ID real de la oferta
                        amount = payment.transactions[0].amount.total.toDouble(),
                        currency = payment.transactions[0].amount.currency,
                        state = payment.state,
                        description = payment.transactions[0].description,
                        dateCreated = Date().toString(),
                        userId = UUID.randomUUID(), // Reemplazar con el ID real del usuario,
                        paymentMethod = "PayPal",
                        orderId = payment.transactions[0].relatedResources[0].sale.id,
                    )
                transactionRepository!!.save<TransactionEntity>(transaction)

                return "Pago exitoso"
            } else {
                return "Error en el pago"
            }
        } catch (e: PayPalRESTException) {
            e.printStackTrace()
            return "Error en el pago"
        }

    }

    @GetMapping("/cancel")
    fun cancelPay(): String {
        return "Pago cancelado"
    }
}
