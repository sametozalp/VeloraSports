package com.ozalp.velorasports.presentation.state

import com.ozalp.velorasports.domain.model.Athlete

data class LoginState(
    var isLoading: Boolean = false,
    var error: String? = null,
    var athlete: Athlete? = null
)
