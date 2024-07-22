package com.prograweb.bidder.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectedEvent

@Component
class WebSocketEventListener(@Autowired private val messagingTemplate: SimpMessageSendingOperations) {

    @EventListener
    fun handleWebSocketConnectListener(event: SessionConnectedEvent) {
        println("Received a new web socket connection, client push a bid")
    }

    @EventListener
    fun handleWebSocketDisconnectListener(event: SessionConnectedEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        val username = headerAccessor.sessionAttributes?.get("username") as String?
        if ( username != null){
            println("User Disconnected: $username")
        }
    }
}