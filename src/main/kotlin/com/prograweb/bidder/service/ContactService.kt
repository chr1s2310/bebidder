package com.prograweb.bidder.service

import com.prograweb.bidder.model.mapper.ContactMapper.toAttended
import com.prograweb.bidder.model.mapper.ContactMapper.toEntity
import com.prograweb.bidder.model.mapper.ContactMapper.toResponse
import com.prograweb.bidder.model.request.ContactRequest
import com.prograweb.bidder.model.response.ContactResponse
import com.prograweb.bidder.repository.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContactService(@Autowired private val contactRepository: ContactRepository) : ContactServiceInterface {

    override fun getAllContacs(): List<ContactResponse> {
        try {
            val contacts = contactRepository.findAll()
            //return only contacta that are not attended
            return contacts.filter { !it.attended }.map { it.toResponse() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getContact(publicId: UUID): ContactResponse {
        try {
            val contact = contactRepository.findByPublicId(publicId) ?: throw Exception("Contacto no encontrado")
            return contact.toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun saveContact(contact: ContactRequest): ContactResponse {
        try {
            return contactRepository.save(contact.toEntity()).toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun updateContact(publicId: UUID): ContactResponse {
        try {
            val contact = contactRepository.findByPublicId(publicId) ?: throw Exception("Contacto no encontrado")
            val contactAttended = contact.toAttended()
            return contactRepository.save(contactAttended).toResponse()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun deleteContact(publicId: UUID) {
        try {
            val contact = contactRepository.findByPublicId(publicId) ?: throw Exception("Contacto no encontrado")
            contactRepository.delete(contact)
        } catch (e: Exception) {
            throw e
        }
    }
}