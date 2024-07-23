package com.prograweb.bidder.service

import com.prograweb.bidder.model.entities.BidEntity
import com.prograweb.bidder.model.mapper.BidMapper.toAddSuscriptor
import com.prograweb.bidder.model.mapper.BidMapper.toCloseBid
import com.prograweb.bidder.model.mapper.BidMapper.toEntity
import com.prograweb.bidder.model.mapper.BidMapper.toOpenBid
import com.prograweb.bidder.model.mapper.BidMapper.toPushBid
import com.prograweb.bidder.model.mapper.BidMapper.toResponse
import com.prograweb.bidder.model.request.BidRequest
import com.prograweb.bidder.model.response.BidHistoryResponse
import com.prograweb.bidder.model.response.BidResponse
import com.prograweb.bidder.model.response.TimeResponse
import com.prograweb.bidder.repository.BidRepository
import com.prograweb.bidder.repository.ProductRepository
import com.prograweb.bidder.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import kotlin.collections.HashMap

@Service
class BidService(
        @Autowired private val bidRepository: BidRepository,
        @Autowired private val productRepository: ProductRepository,
        @Autowired private val userRepository: UserRepository
) : BidServiceInterface {

    private val bidHashMap: HashMap<UUID, Int> = HashMap()

    private fun autoStartBid(bid: BidEntity) {
        val executionDate = Date.from(bid.initBidDate.atZone(ZoneId.systemDefault()).toInstant())

        Timer().schedule(object : TimerTask() {
            override fun run() {
                bidRepository.save(bid.toOpenBid())
                bidHashMap.put(bid.publicId, 30)

                val updater = Timer()

                updater.schedule(object : TimerTask() {
                    override fun run() {
                        val timeLeft = bidHashMap.get(bid.publicId)!! - 1
                        bidHashMap.put(bid.publicId, timeLeft)

                        if (timeLeft == -1) {
                            bidRepository.save(bid.toCloseBid())
                            updater.cancel()
                            bidHashMap.remove(bid.publicId)
                        }
                    }
                }, 0, 1000)
            }
        }, executionDate)
    }

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
            autoStartBid(bidSaved);
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

            bidHashMap.put(bidSaved.publicId, 30);

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

    override fun getBidHistory(publicId: UUID): List<BidHistoryResponse> {
        try {
            val user = userRepository.findByPublicId(publicId) ?: throw Exception("Usuario no encontrado")
            val bids = bidRepository.findBySuscriptorsPublicId(publicId) ?: throw Exception("Pujas no encontradas")

            val historyBids = bids.map { bid ->
                BidHistoryResponse(
                    bidId = bid.publicId.toString(),
                    winningUser = bid.winningUser?.publicId.toString(),
                    mount = bid.amount.toDouble(),
                    date = bid.initBidDate.toString(),
                    product = bid.productEntity
                )
            }
            val historialPujas = historyBids.sortedByDescending { it.mount }
            return historialPujas
        } catch (e: Exception) {
            throw e
        }

    }

    override fun remainingTime(publicId: UUID): TimeResponse {
        return TimeResponse(remainingTime = bidHashMap.getOrDefault(publicId, -1))
    }
}

private operator fun LocalDateTime.compareTo(date: Date): Int {
    return this.compareTo(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
}
