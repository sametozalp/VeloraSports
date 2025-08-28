package com.ozalp.velorasports.data.remote.dto.response.abstracts

data class VeloraResponse<T>(
    val data: T?,
    val message: String?,
    val success: Boolean
)
