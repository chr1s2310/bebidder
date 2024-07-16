package com.prograweb.bidder.model.mapper

import com.prograweb.bidder.model.entities.BidEntity
import com.prograweb.bidder.model.entities.ProductEntity
import com.prograweb.bidder.model.mapper.ProductMapper.toResponse
import com.prograweb.bidder.model.request.BidRequest
import com.prograweb.bidder.model.response.BidResponse

object BidMapper {

    fun BidRequest.toEntity(productEntity: ProductEntity) : BidEntity {
        return BidEntity(
            amount = this.amount,
            productEntity = productEntity
        )
    }

    fun BidEntity.toResponse() : BidResponse {
        return BidResponse(
            id = this.id!!,
            publicId = this.publicId,
            amount = this.amount,
            lastUserBid = this.lastUserBid,
            product = this.productEntity.toResponse(),
            closed = this.closed
        )
    }

    fun BidRequest.toPushBid(bidEntity: BidEntity) : BidEntity {
        return BidEntity(
            amount = this.amount,
            productEntity = bidEntity.productEntity,
            lastUserBid = this.lastUserBid
        )
    }
}