package com.prograweb.bidder.service

import com.prograweb.bidder.model.entities.TransactionEntity
import com.prograweb.bidder.repository.TransactionRepository
import jakarta.persistence.Id
import org.apache.tomcat.util.descriptor.web.ContextTransaction
import org.springframework.beans.factory.annotation.Autowired

abstract class TransactionService(@Autowired private val transactionRepository: TransactionRepository): TransactionInterface{

    override fun saveTransaction(transactionEntity: TransactionEntity) {
        transactionRepository.save(transactionEntity)
    }


}