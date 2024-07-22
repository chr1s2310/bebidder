package com.prograweb.bidder.controller

import com.prograweb.bidder.model.request.ContactRequest
import com.prograweb.bidder.model.response.ContactResponse
import com.prograweb.bidder.service.ContactServiceInterface
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/contact")
class ContactController(@Autowired private val contactService: ContactServiceInterface) {

    @GetMapping
    fun getAllCon(): ResponseEntity<List<ContactResponse>> {
        return ResponseEntity.ok(contactService.getAllContacs())
    }

    @GetMapping("/{publicId}")
    fun getContactById(@PathVariable publicId: UUID): ResponseEntity<ContactResponse> {
        return ResponseEntity.ok(contactService.getContact(publicId))
    }

    @PostMapping
    fun saveContact(@Valid @RequestBody contact: ContactRequest): ResponseEntity<ContactResponse> {
        return ResponseEntity.ok(contactService.saveContact(contact))
    }

    @PutMapping("/{publicId}")
    fun updateContact(@PathVariable publicId: UUID): ResponseEntity<ContactResponse> {
        return ResponseEntity.ok(contactService.updateContact(publicId))
    }

    @DeleteMapping("/{publicId}")
    fun deleteContact(@PathVariable publicId: UUID): ResponseEntity<Any> {
        return ResponseEntity.ok(contactService.deleteContact(publicId))
    }
}