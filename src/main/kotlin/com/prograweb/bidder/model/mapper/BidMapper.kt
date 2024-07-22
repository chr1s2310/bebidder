package com.prograweb.bidder.model.mapper

import com.prograweb.bidder.model.entities.BidEntity
import com.prograweb.bidder.model.entities.ProductEntity
import com.prograweb.bidder.model.entities.UserEntity
import com.prograweb.bidder.model.mapper.ProductMapper.toResponse
import com.prograweb.bidder.model.request.BidRequest
import com.prograweb.bidder.model.response.BidResponse
import java.text.SimpleDateFormat

object BidMapper {

    fun BidRequest.toEntity(productEntity: ProductEntity) : BidEntity {
        return BidEntity(
            amount = productEntity.price,
            productEntity = productEntity,
            initBidDate = SimpleDateFormat("dd-MM-yyyy").parse(this.initBidDate)
        )
    }

    fun BidEntity.toResponse() : BidResponse {
        return BidResponse(
            id = this.id!!,
            publicId = this.publicId,
            amount = this.amount,
            userPublicId = this.winningUser?.publicId,
            product = this.productEntity.toResponse(),
            closed = this.closed,
            initBidDate = SimpleDateFormat("dd-MM-yyyy").format(this.initBidDate)
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