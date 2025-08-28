package com.ozalp.velorasports.presentation.state

data class RegisterState(
    var isLoading: Boolean = false,
    var error: String? = null,
    var success: Boolean? = null
)