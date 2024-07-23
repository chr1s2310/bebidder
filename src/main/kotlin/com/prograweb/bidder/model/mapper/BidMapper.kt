package com.prograweb.bidder.model.mapper

import com.prograweb.bidder.model.entities.BidEntity
import com.prograweb.bidder.model.entities.ProductEntity
import com.prograweb.bidder.model.entities.UserEntity
import com.prograweb.bidder.model.mapper.ProductMapper.toResponse
import com.prograweb.bidder.model.request.BidRequest
import com.prograweb.bidder.model.response.BidResponse
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object BidMapper {

    fun BidRequest.toEntity(productEntity: ProductEntity) : BidEntity {
        // Usa LocalDateTime para manejar fechas sin zonas horarias
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        val initBidDate = LocalDateTime.parse(this.initBidDate, dateTimeFormatter)
        println(initBidDate)
        return BidEntity(
            amount = productEntity.price,
            productEntity = productEntity,
            initBidDate = initBidDate
        )
    }

    fun BidEntity.toResponse() : BidResponse {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        return BidResponse(
            id = this.id!!,
            publicId = this.publicId,
            amount = this.amount,
            userPublicId = this.winningUser?.publicId,
            product = this.productEntity.toResponse(),
            closed = this.closed,
            initBidDate = this.initBidDate.format(dateTimeFormatter)
        )
    }

    fun BidRequest.toPushBid(bidEntity: BidEntity, userEntity: UserEntity) : BidEntity {
        bidEntity.amount += this.amount
        bidEntity.winningUser = userEntity
        return bidEntity
    }

    fun BidEntity.toCloseBid() : BidEntity {
        this.closed = true
        return this
    }
}