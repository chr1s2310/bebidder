package com.prograweb.bidder.repository

import com.prograweb.bidder.model.entities.BidEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BidRepository:JpaRepository<BidEntity, Long> {

    fun findByPublicId(publicId: UUID): BidEntity?

    fun findByUserBidPublicId(userPublicId: UUID): List<BidEntity>

    fun findByProductEntityPublicId(publicId: UUID): BidEntity?

    fun findByClosed(closed: Boolean): List<BidEntity>
}