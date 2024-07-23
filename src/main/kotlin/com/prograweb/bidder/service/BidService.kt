package com.prograweb.bidder.service

import com.prograweb.bidder.model.mapper.BidMapper.toAddSuscriptor
import com.prograweb.bidder.model.mapper.BidMapper.toCloseBid
import com.prograweb.bidder.model.mapper.BidMapper.toEntity
import com.prograweb.bidder.model.mapper.BidMapper.toPushBid
import com.prograweb.bidder.model.mapper.BidMapper.toResponse
import com.prograweb.bidder.model.request.BidRequest
import com.prograweb.bidder.model.response.BidResponse
import com.prograweb.bidder.repository.BidRepository
import com.prograweb.bidder.repository.ProductRepository
import com.prograweb.bidder.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class BidService(
        @Autowired private val bidRepository: BidRepository,
        @Autowired private val productRepository: ProductRepository,
        @Autowired private val userRepository: UserRepository
) : BidServiceInterface {

    override fun getAll(): List<BidResponse> {
        try {
            return bidRepository.findAll().map { it.toResponse() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getBid(publicId: UUID): BidResponse {
        try {
            val bid = bidRepository.findByPublicId(publicId) ?: throw Exception("Puja no encontrada")
            return bid.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun saveBid(bidRequest: BidRequest): BidResponse {
        try {
            val product = productRepository.findByPublicId(bidRequest.productPublicId) ?: throw Exception("Producto no encontrado")
            val bidEntity = bidRequest.toEntity(product)
            product.href = "/item/${bidEntity.publicId}"
            productRepository.save(product)
            val bidSaved = bidRepository.save(bidEntity)
            return bidSaved.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun deleteBid(publicId: UUID) {
        try {
            val bid = bidRepository.findByPublicId(publicId) ?: throw Exception("Puja no encontrada")
            if (bid.winningUser != null) {
                throw Exception("La puja ya se encuetra procesada, no se puede elminar")
            }
            bidRepository.delete(bid)
        } catch (e: Exception) {
            throw e
        }
    }

    override fun updateBid(publicId: UUID, bidRequest: BidRequest): BidResponse {
        try {
            val bidEntity = bidRepository.findByPublicId(publicId) ?: throw Exception("Puja no encontrada")
            val user = userRepository.findByPublicId(bidRequest.userPublicId!!) ?: throw Exception("Usuario no encontrado")
            val bidUpdated = bidRequest.toPushBid(bidEntity, user)
            val bidSaved = bidRepository.save(bidUpdated)
            return bidSaved.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun closeBid(publicId: UUID): BidResponse {
        try {
            val bid = bidRepository.findByPublicId(publicId) ?: throw Exception("Puja no encontrada")
            val bidSaved = bidRepository.save(bid.toCloseBid())
            return bidSaved.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getBidsInitPending(): List<BidResponse> {
        try {
            val bids = bidRepository.findByInitBidDateGreaterThanEqual(Date())
            return bids.map { it.toResponse() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getBidsByUser(userPublicId: UUID): List<BidResponse> {
        try {
            val bids = bidRepository.findBySuscriptorsPublicId(userPublicId).filter { it.initBidDate > Date() || !it.closed }.sortedBy { it.initBidDate }
            return bids.map { it.toResponse() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun addSuscriptor(publicId: UUID, userPublicId: UUID): BidResponse {
        try {
            val bid = bidRepository.findByPublicId(publicId) ?: throw Exception("Puja no encontrada")
            val user = userRepository.findByPublicId(userPublicId) ?: throw Exception("Usuario no encontrado")
            val bidUpdated = bid.toAddSuscriptor(user)
            user.suscriptions.add(bid)
            userRepository.save(user)
            val bidSaved = bidRepository.save(bidUpdated)
            return bidSaved.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getBidsByUserNoSuscribed(userPublicId: UUID): List<BidResponse> {
        try {
            val bids = bidRepository.findAll()
            val user = userRepository.findByPublicId(userPublicId) ?: throw Exception("Usuario no encontrado")
            val bidsNoSuscribed = bids.filter { !user.suscriptions.contains(it) }.filter { it.initBidDate > Date() }
            return bidsNoSuscribed.map { it.toResponse() }
        } catch (e: Exception) {
            throw e
        }
    }
}

private operator fun LocalDateTime.compareTo(date: Date): Int {
    return this.compareTo(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
}
