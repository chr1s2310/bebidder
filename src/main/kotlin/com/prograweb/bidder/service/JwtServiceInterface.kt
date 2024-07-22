package com.prograweb.bidder.service

interface JwtServiceInterface {

    fun generateToken(email: String): String

    fun validateToken(token: String): Boolean

    fun getEmailFromToken(token: String): String?

}