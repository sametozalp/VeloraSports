package com.ozalp.velorasports.data.remote.api

import com.ozalp.velorasports.data.remote.dto.request.concretes.CreateUserRequest
import com.ozalp.velorasports.data.remote.dto.response.abstracts.Response
import com.ozalp.velorasports.data.remote.dto.response.concretes.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST("/api/user/create")
    suspend fun getUser(@Body createUserRequest: CreateUserRequest): Response<UserResponse>
}