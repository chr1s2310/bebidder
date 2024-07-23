package com.prograweb.bidder.model.request

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.*

class TransactionRequest (


    @field:NotNull(message = "El nombre del producto es requerido")
    @field:NotBlank(message = "El nombre del producto no puede estar vacío")
    val orderId: String,


    @field:NotNull(message = "El identificador del usuario es requerido")
    @field:NotBlank(message = "El identificador del usuario no puede estar vacío")
    val userId: UUID,

    @field:NotNull(message = "El identificador del producto es requerido")
    @field:NotBlank(message = "El identificador del proyecto no puede estar vacío")
    var productId: String,

    @field:NotNull(message = "El identificador del pago es requerido")
    @field:NotBlank(message = "El identificador del pago no puede estar vacío")
    var paymentId: String,


    @field:NotNull(message = "El identificador del usuario es requerido")
    @field:NotBlank(message = "El identificador del usuario no puede estar vacío")
    var payerId: String,

    @field:NotNull(message = "El monto es requerido")
    @field:NotBlank(message = "El monto no puede estar vacío")
    var amount: Double,

    @field:NotNull(message = "La moneda es requerida")
    @field:NotBlank(message = "La moneda no puede estar vacío")
    var currency: String,

    @field:NotNull(message = "El estado es requerido")
    @field:NotBlank(message = "El estado no puede estar vacío")
    var state: String,

    var bidId: UUID,

    var description: String,


    var method: String,


    var status: String,


    var dateCreated: Date,
)