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
            userBid = this.userBid?.name,
            product = this.productEntity.toResponse(),
            closed = this.closed,
            initBidDate = SimpleDateFormat("dd-MM-yyyy").format(this.initBidDate)
        )
    }

    fun BidRequest.toPushBid(bidEntity: BidEntity, userEntity: UserEntity) : BidEntity {
        return BidEntity(
            amount = bidEntity.amount + this.amount,
            productEntity = bidEntity.productEntity,
            userBid = userEntity,
            initBidDate = bidEntity.initBidDate
        )
    }
}