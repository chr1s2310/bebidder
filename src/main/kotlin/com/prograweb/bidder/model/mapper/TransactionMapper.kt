package com.prograweb.bidder.model.mapper

import com.paypal.api.payments.Payment
import com.prograweb.bidder.model.entities.CategoryEntity
import com.prograweb.bidder.model.entities.ProductEntity
import com.prograweb.bidder.model.entities.TransactionEntity
import com.prograweb.bidder.model.request.ProductRequest
import com.prograweb.bidder.model.request.TransactionRequest

object TransactionMapper {

    fun TransactionRequest.toEntity(payment: Payment) : TransactionEntity {
        return TransactionEntity(
            orderId = payment.transactions[0].relatedResources[0].sale.id,
            amount = payment.transactions[0].amount.total.toDouble(),
            status = payment.transactions[0].relatedResources[0].sale.state,
            currency = payment.transactions[0].amount.currency,
            dateCreated = payment.transactions[0].relatedResources[0].sale.createTime,
            description = payment.transactions[0].description,
            paymentMethod = this.method,
            state = payment.state,
            paymentId = payment.id,
            payerId = payment.payer.payerInfo.payerId,
            bidId = this.userId,
            userId = this.userId
        )
    }
}