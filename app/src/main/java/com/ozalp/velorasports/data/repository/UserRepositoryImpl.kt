package com.ozalp.velorasports.data.repository

import com.ozalp.velorasports.data.remote.api.UserAPI
import com.ozalp.velorasports.data.remote.dto.request.concretes.CreateUserRequest
import com.ozalp.velorasports.data.remote.dto.response.abstracts.Response
import com.ozalp.velorasports.data.remote.dto.response.concretes.UserResponse
import com.ozalp.velorasports.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val api: UserAPI) : UserRepository {
    override suspend fun getUser(createUserRequest: CreateUserRequest): Response<UserResponse> {
        return api.getUser(createUserRequest)
    }
}