package com.ozalp.velorasports.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozalp.velorasports.data.remote.dto.request.concretes.CreateUserRequest
import com.ozalp.velorasports.domain.usecase.GetUserUseCase
import com.ozalp.velorasports.presentation.state.RegisterState
import com.ozalp.velorasports.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val getUserUseCase: GetUserUseCase) : ViewModel() {

    private var _registerState by mutableStateOf(RegisterState())
    val registerState get() = _registerState


    fun createUser(createUserRequest: CreateUserRequest) {
        viewModelScope.launch {

            _registerState = RegisterState(isLoading = true)

            val result = getUserUseCase(createUserRequest)

            when (result) {
                is Resource.Success -> {
                    _registerState = _registerState.copy(
                        success = true,
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _registerState = _registerState.copy(
                        error = result.message,
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _registerState = _registerState.copy(
                        isLoading = true,
                    )
                }
            }
        }
    }

}