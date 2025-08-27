package com.ozalp.velorasports.domain.dto.request.concretes

data class CreateUserRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val phone: String
)