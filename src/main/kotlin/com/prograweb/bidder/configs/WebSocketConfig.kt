package com.prograweb.bidder.configs

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig: WebSocketMessageBrokerConfigurer {

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/webSocketApp").setAllowedOriginPatterns("*").withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.setApplicationDestinationPrefixes("/app")
        registry.enableStompBrokerRelay("/topic").setRelayHost("localhost")
                .setRelayPort(61613).setClientLogin("guest").setClientPasscode("guest")
    }
}