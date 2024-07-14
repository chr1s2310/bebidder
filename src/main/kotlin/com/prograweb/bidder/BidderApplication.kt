package com.prograweb.bidder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class BidderApplication

fun main(args: Array<String>) {
	runApplication<BidderApplication>(*args)
	println("Servidor iniciado...")
}
