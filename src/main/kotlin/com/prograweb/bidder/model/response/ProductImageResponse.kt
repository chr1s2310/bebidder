package com.prograweb.bidder.model.response

import java.util.UUID

class ProductImageResponse (

        val id: Long,

        val publicId: UUID,

        val image: ByteArray
)