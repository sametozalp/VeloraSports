package com.ozalp.velorasports.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozalp.velorasports.data.remote.dto.request.concretes.LoginUserRequest
import com.ozalp.velorasports.domain.usecase.LoginUseCase
import com.ozalp.velorasports.presentation.state.LoginState
import com.ozalp.velorasports.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase) : ViewModel() {

    private var _loginState by mutableStateOf(LoginState())
    val loginState get() = _loginState

    fun login(email: String, password: String) {

        _loginState = _loginState.copy(isLoading = true)

        viewModelScope.launch {

            val result = loginUseCase(LoginUserRequest(email, password))

            when (result) {
                is Resource.Success -> {
                    _loginState = _loginState.copy(
                        athlete = result.data,
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _loginState = _loginState.copy(
                        error = result.message,
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _loginState = _loginState.copy(
                        isLoading = true,
                    )
                }
            }
        }

    }
}