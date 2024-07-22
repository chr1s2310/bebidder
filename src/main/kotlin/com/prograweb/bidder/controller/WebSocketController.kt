package com.prograweb.bidder.controller

import com.prograweb.bidder.model.request.BidRequest
import com.prograweb.bidder.model.response.BidResponse
import com.prograweb.bidder.service.BidServiceInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.stereotype.Controller

@Controller
class WebSocketController(@Autowired private val bidServiceInterface: BidServiceInterface) {

    @MessageMapping("/push_bid")
    @SendTo("/topic/bid")
    fun pushBid(@Payload bidRequest: BidRequest): BidResponse {
        return bidServiceInterface.updateBid(bidRequest.publicId!!, bidRequest)
    }

    @MessageMapping("/new_user")
    @SendTo("/topic/bid")
    fun newUser(@Payload bidRequest: BidRequest, headerAccessor: SimpMessageHeaderAccessor): BidResponse {
        headerAccessor.sessionAttributes?.set("username", bidRequest.userPublicId)
        return BidResponse(userPublicId = bidRequest.userPublicId, initBidDate = bidRequest.initBidDate)
    }
}