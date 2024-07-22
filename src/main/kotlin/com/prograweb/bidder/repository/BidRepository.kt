package com.prograweb.bidder.repository

import com.prograweb.bidder.model.entities.BidEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BidRepository:JpaRepository<BidEntity, Long> {

    fun findByPublicId(publicId: UUID): BidEntity?

    fun findByWinningUserPublicId(userPublicId: UUID): List<BidEntity>

    fun findByProductEntityPublicId(publicId: UUID): BidEntity?

    fun findByClosed(closed: Boolean): List<BidEntity>

    fun findBySuscriptorsPublicId(userPublicId: UUID): List<BidEntity>

    fun findByInitBidDateGreaterThanEqual(date: Date): List<BidEntity>
}