package com.prograweb.bidder.service

import com.prograweb.bidder.model.request.ContactRequest
import com.prograweb.bidder.model.response.ContactResponse
import java.util.UUID

interface ContactServiceInterface {

    fun getAllContacs(): List<ContactResponse>

    fun getContact(publicId: UUID): ContactResponse

    fun saveContact(contact: ContactRequest): ContactResponse

    fun updateContact(publicId: UUID): ContactResponse

    fun deleteContact(publicId: UUID)
}