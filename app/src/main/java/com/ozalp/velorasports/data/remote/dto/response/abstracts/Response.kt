package com.ozalp.velorasports.data.remote.dto.response.abstracts

import com.ozalp.velorasports.data.remote.dto.response.concretes.UserResponse

data class Response<T>(
    val userResponse: UserResponse,
    val message: Any,
    val success: Boolean
)
