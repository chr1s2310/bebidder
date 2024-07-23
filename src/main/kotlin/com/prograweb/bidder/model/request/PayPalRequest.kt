package com.prograweb.bidder.model.request

import com.paypal.api.payments.Payment
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class PayPalRequest(
    @field:NotNull(message = "El nombre del producto es requerido")
    @field:NotBlank(message = "El nombre del producto no puede estar vacío")
    val amount: Double,
    @field:NotNull(message = "La moneda es requerida")
    @field:NotBlank(message = "La moneda no puede estar vacío")
    val currency: String,
    @field:NotNull(message = "El método de pago es requerido")
    @field:NotBlank(message = "El método de pago no puede estar vacío")
    val method: String,
    @field:NotNull(message = "El intent es requerido")
    @field:NotBlank(message = "El intent no puede estar vacío")
    val intent: String,
    @field:NotNull(message = "La descripción es requerida")
    @field:NotBlank(message = "La descripción no puede estar vacío")
    val description: String,
    val cancelUrl: String,
    val successUrl: String,

)


