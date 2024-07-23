package com.prograweb.bidder.controller

import com.prograweb.bidder.model.request.BidRequest
import com.prograweb.bidder.model.response.BidResponse
import com.prograweb.bidder.model.response.TimeResponse
import com.prograweb.bidder.service.BidServiceInterface
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/bids")
class BidController(@Autowired private val bidServiceInterface: BidServiceInterface) {

    @GetMapping
    fun getAll(): ResponseEntity<List<BidResponse>> {
        return ResponseEntity.ok(bidServiceInterface.getAll())
    }

    @GetMapping("/{publicId}")
    fun getBidById(@PathVariable publicId: UUID): ResponseEntity<BidResponse> {
        return ResponseEntity.ok(bidServiceInterface.getBid(publicId))
    }

    @PostMapping
    fun saveBid(@Valid @RequestBody bidRequest: BidRequest): ResponseEntity<BidResponse> {
        return ResponseEntity.ok(bidServiceInterface.saveBid(bidRequest))
    }

    @DeleteMapping("/{publicId}")
    fun deleteBid(@PathVariable publicId: UUID): ResponseEntity<Any> {
        return ResponseEntity.ok(bidServiceInterface.deleteBid(publicId))
    }

    @PutMapping("/{publicId}")
    fun updateBid(@PathVariable publicId: UUID, @Valid @RequestBody bidRequest: BidRequest): ResponseEntity<BidResponse> {
        return ResponseEntity.ok(bidServiceInterface.updateBid(publicId, bidRequest))
    }

    @PutMapping("/{publicId}/close")
    fun closeBid(@PathVariable publicId: UUID): ResponseEntity<BidResponse> {
        return ResponseEntity.ok(bidServiceInterface.closeBid(publicId))
    }

    @GetMapping("/user/{publicId}")
    fun getBidsByUser(@PathVariable publicId: UUID): ResponseEntity<List<BidResponse>> {
        return ResponseEntity.ok(bidServiceInterface.getBidsByUser(publicId))
    }

    @PutMapping("/{publicId}/suscriptor/{userPublicId}")
    fun addSuscriptor(@PathVariable publicId: UUID, @PathVariable userPublicId: UUID): ResponseEntity<BidResponse> {
        return ResponseEntity.ok(bidServiceInterface.addSuscriptor(publicId, userPublicId))
    }

    @GetMapping("/user/{publicId}/no-suscribed")
    fun getBidsByUserNoSuscribed(@PathVariable publicId: UUID): ResponseEntity<List<BidResponse>> {
        return ResponseEntity.ok(bidServiceInterface.getBidsByUserNoSuscribed(publicId))
    }

    @GetMapping("/{publicId}/time")
    fun getRemainingTime(@PathVariable publicId: UUID): ResponseEntity<TimeResponse> {
        return ResponseEntity.ok(bidServiceInterface.remainingTime(publicId))
    }
}