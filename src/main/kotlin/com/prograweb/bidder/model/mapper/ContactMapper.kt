package com.prograweb.bidder.model.mapper

import com.prograweb.bidder.model.entities.ContactEntity
import com.prograweb.bidder.model.request.ContactRequest
import com.prograweb.bidder.model.response.ContactResponse

object ContactMapper {

    fun ContactRequest.toEntity(): ContactEntity {
        return ContactEntity(
            email = this.email,
            subject = this.subject,
                attended = false
        )
    }

    fun ContactEntity.toResponse(): ContactResponse {
        return ContactResponse(
            id = this.id!!,
                publicId = this.publicId,
            email = this.email,
            subject = this.subject,
            attended = this.attended
        )
    }

    fun ContactEntity.toAttended(): ContactEntity {
        this.attended = true
        return this
    }
}