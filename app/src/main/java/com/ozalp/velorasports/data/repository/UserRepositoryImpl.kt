package com.ozalp.velorasports.data.repository

import com.ozalp.velorasports.data.remote.api.UserAPI
import com.ozalp.velorasports.data.remote.dto.request.concretes.CreateUserRequest
import com.ozalp.velorasports.data.remote.dto.request.concretes.LoginUserRequest
import com.ozalp.velorasports.data.remote.dto.response.abstracts.VeloraResponse
import com.ozalp.velorasports.data.remote.dto.response.concretes.AthleteResponse
import com.ozalp.velorasports.data.remote.dto.response.concretes.UserResponse
import com.ozalp.velorasports.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val api: UserAPI) : UserRepository {
    override suspend fun getUser(createUserRequest: CreateUserRequest): Response<VeloraResponse<UserResponse>> {
        return api.getUser(createUserRequest)
    }

    override suspend fun login(loginUserRequest: LoginUserRequest): Response<VeloraResponse<AthleteResponse>> {
        return api.login(loginUserRequest)
    }
}