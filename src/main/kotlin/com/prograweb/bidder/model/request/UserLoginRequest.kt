package com.prograweb.bidder.model.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class UserLoginRequest (

    @field:NotNull(message = "El email es requerido")
    @field:NotBlank(message = "El email no puede estar vacío")
    var email: String,

    @field:NotNull(message = "La contraseña es requerida")
    @field:NotBlank(message = "El campo de la contraseña no puede estar vacío")
    var password: String
)