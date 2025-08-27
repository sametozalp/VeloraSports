package com.ozalp.velorasports.domain.dto.response.abstracts

import com.ozalp.velorasports.domain.dto.response.concretes.UserResponse

data class Response<T>(
    val userResponse: UserResponse,
    val message: Any,
    val success: Boolean
)
