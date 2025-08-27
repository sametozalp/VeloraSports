package com.ozalp.velorasports.domain.repository

import com.ozalp.velorasports.data.remote.dto.request.concretes.CreateUserRequest
import com.ozalp.velorasports.data.remote.dto.response.abstracts.Response
import com.ozalp.velorasports.data.remote.dto.response.concretes.UserResponse

interface UserRepository {

    suspend fun getUser(createUserRequest: CreateUserRequest): Response<UserResponse>

}