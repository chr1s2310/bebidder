package com.prograweb.bidder.repository

import com.prograweb.bidder.model.entities.TransactionEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TransactionRepository: JpaRepository<TransactionEntity, Long> {
    fun findByTransactionId(transactionId: UUID): TransactionEntity

}