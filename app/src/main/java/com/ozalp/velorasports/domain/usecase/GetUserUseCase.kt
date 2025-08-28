package com.ozalp.velorasports.domain.usecase

import com.google.gson.Gson
import com.ozalp.velorasports.data.remote.dto.request.concretes.CreateUserRequest
import com.ozalp.velorasports.data.remote.dto.response.abstracts.VeloraResponse
import com.ozalp.velorasports.data.remote.dto.response.concretes.UserResponse
import com.ozalp.velorasports.domain.model.User
import com.ozalp.velorasports.domain.repository.UserRepository
import com.ozalp.velorasports.util.Resource
import retrofit2.Response
import javax.inject.Inject

class GetUserUseCase @Inject constructor(val userRepository: UserRepository) {

    suspend operator fun invoke(request: CreateUserRequest): Resource<UserResponse> {
        val response = userRepository.getUser(request)
        return response
    }

}