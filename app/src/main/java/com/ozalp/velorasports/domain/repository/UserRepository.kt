package com.ozalp.velorasports.domain.repository

import com.ozalp.velorasports.data.remote.dto.request.concretes.CreateUserRequest
import com.ozalp.velorasports.data.remote.dto.response.abstracts.VeloraResponse
import com.ozalp.velorasports.data.remote.dto.response.concretes.UserResponse
import retrofit2.Response

interface UserRepository {

    suspend fun getUser(createUserRequest: CreateUserRequest): Response<VeloraResponse<UserResponse>>

}