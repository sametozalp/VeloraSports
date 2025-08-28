package com.ozalp.velorasports.data.remote.dto.response.abstracts

import com.ozalp.velorasports.data.remote.dto.response.concretes.UserResponse

data class Response<T>(
    val data: T?,
    val message: Any?,
    val success: Boolean
)
