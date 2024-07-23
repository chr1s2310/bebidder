package com.prograweb.bidder.service

import com.prograweb.bidder.model.entities.TransactionEntity
import jakarta.persistence.Id
import java.util.UUID

interface TransactionInterface {

    fun saveTransaction(transactionEntity: TransactionEntity)
    fun getTransaction(transactionId: UUID): TransactionEntity
    fun deleteTransaction(transactionId: UUID)
}