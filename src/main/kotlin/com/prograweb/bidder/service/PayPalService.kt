package com.prograweb.bidder.service

import com.paypal.api.payments.*
import com.paypal.base.rest.APIContext
import com.paypal.base.rest.PayPalRESTException
import com.prograweb.bidder.model.request.PayPalRequest
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.*


@Service
@RequiredArgsConstructor
class PayPalService(private val apiContext: APIContext): PayPalInterface {

    @Throws(PayPalRESTException::class)
    override fun createPayment(request: PayPalRequest): Payment {
        val amount = Amount()
        amount.setCurrency(request.currency)
        amount.setTotal(String.format(Locale.US, "%.2f", request.amount)) // 9.99$ - 9,99€
        println(amount.toString())
        val transaction = Transaction()
        transaction.setDescription(request.description)
        transaction.setAmount(amount)

        val transactions: MutableList<Transaction> = ArrayList()
        transactions.add(transaction)

        val payer = Payer()
        payer.setPaymentMethod(request.method)

        val payment = Payment()
        payment.setIntent(request.intent)
        payment.setPayer(payer)
        payment.setTransactions(transactions)

        val redirectUrls = RedirectUrls()
        redirectUrls.setCancelUrl(request.cancelUrl)
        redirectUrls.setReturnUrl(request.successUrl)
        payment.setRedirectUrls(redirectUrls)
        return payment.create(apiContext)
    }
    @Throws(PayPalRESTException::class)
    override fun executePayment(
        paymentId: String,
        payerId: String
    ): Payment {
        val payment = Payment()
        payment.setId(paymentId)

        val paymentExecution = PaymentExecution()
        paymentExecution.setPayerId(payerId)

        return payment.execute(apiContext, paymentExecution)
    }
}