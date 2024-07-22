package com.prograweb.bidder.repository

import com.prograweb.bidder.model.entities.ContactEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ContactRepository: JpaRepository<ContactEntity, Long> {

    fun findByPublicId(publicId: UUID): ContactEntity?
}