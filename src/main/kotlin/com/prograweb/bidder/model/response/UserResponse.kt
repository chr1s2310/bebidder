package com.prograweb.bidder.model.response

import java.util.UUID


class UserResponse (

    val id: Long,

    val publicId: UUID,

    val profilePicture: String? = null,

    val email: String,

    val name: String,

    val lastname: String,

    val username: String,

    val active: Boolean
)