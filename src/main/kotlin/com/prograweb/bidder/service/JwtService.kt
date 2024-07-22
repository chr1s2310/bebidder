package com.prograweb.bidder.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService: JwtServiceInterface {
    private val secretKey = "1234567890"
    private val validityInMs = 3600000L // 1 hora

    override fun generateToken(email: String): String {
        val now = Date()
        val validity = Date(now.time + validityInMs)

        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    override fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun getEmailFromToken(token: String): String? {
        return try {
            Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .body
                .subject
        } catch (e: Exception) {
            null
        }
    }
}