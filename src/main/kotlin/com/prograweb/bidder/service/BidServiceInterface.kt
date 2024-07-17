package com.prograweb.bidder.service

import com.prograweb.bidder.model.request.BidRequest
import com.prograweb.bidder.model.response.BidResponse
import java.util.UUID

interface BidServiceInterface {

    fun getAll(): List<BidResponse>

    fun getBid(publicId: UUID): BidResponse

    fun saveBid(bidRequest: BidRequest): BidResponse

    fun deleteBid(publicId: UUID)

    fun updateBid(publicId: UUID, bidRequest: BidRequest): BidResponse

    fun closeBid(publicId: UUID): BidResponse
}