package com.prograweb.bidder.model.mapper

import com.prograweb.bidder.model.entities.Producto
import com.prograweb.bidder.model.request.ProductoRequest
import com.prograweb.bidder.model.response.ProductoResponse

object ProductoMapper {

    fun ProductoRequest.toEntity() : Producto {
        return Producto(
            nombre = this.nombre,
            titulo = this.titulo
        )
    }

    fun Producto.toResponse() : ProductoResponse {
        return ProductoResponse(
            id = this.id!!,
            nombre = this.nombre,
            titulo = this.titulo
        )
    }
}