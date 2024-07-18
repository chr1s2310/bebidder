package com.prograweb.bidder.model.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class UserRequest (
    @field:NotNull(message = "El nombre de usuario es requerido")
    @field:NotBlank(message = "El nombre de usuario no puede estar vacío")
    val name: String,

    @field:NotNull(message = "El apellido es requerido")
    @field:NotBlank(message = "El apellido no puede estar vacío")
    val lastname: String,

    @field:NotNull(message = "El nombre de usuario es requerido")
    @field:NotBlank(message = "El nombre de usuario no puede estar vacío")
    val username: String,

    @field:NotNull(message = "El email es requerido")
    @field:NotBlank(message = "El email no puede estar vacío")
    var email: String,

    @field:NotNull(message = "La contraseña es requerida")
    @field:NotBlank(message = "El campo de la contraseña no puede estar vacío")
    var password: String,

    //var provider: String,

    //var providerId: String

)