package com.prograweb.bidder.config

import com.paypal.base.rest.APIContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class PayPalConfig {
    @Value("\${paypal.client.id}")
    private lateinit var clientId: String

    @Value("\${paypal.client.secret}")
    private lateinit var clientSecret: String

    @Value("\${paypal.mode}")
    private lateinit var mode: String
    @Bean
    fun apiContext(): APIContext {
        return APIContext(clientId, clientSecret, mode)
    }
}