package com.prograweb.bidder.model.response

import java.util.UUID

data class ContactResponse(

        val id: Long,

        val publicId: UUID,

        val email: String,

        val subject: String,

        val attended: Boolean
)
