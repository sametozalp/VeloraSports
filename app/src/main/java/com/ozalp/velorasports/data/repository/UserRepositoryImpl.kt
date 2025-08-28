package com.ozalp.velorasports.data.repository

import com.google.gson.Gson
import com.ozalp.velorasports.data.remote.api.UserAPI
import com.ozalp.velorasports.data.remote.dto.request.concretes.CreateUserRequest
import com.ozalp.velorasports.data.remote.dto.response.abstracts.VeloraResponse
import com.ozalp.velorasports.data.remote.dto.response.concretes.UserResponse
import com.ozalp.velorasports.domain.repository.UserRepository
import com.ozalp.velorasports.util.Resource
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val api: UserAPI) : UserRepository {
    override suspend fun getUser(createUserRequest: CreateUserRequest): Resource<UserResponse> {
        return try {
            val response = api.getUser(createUserRequest)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.success && body.data != null) {
                    Resource.Success(body.data)
                } else {
                    Resource.Error(body?.message ?: "Boş veya başarısız response")
                }
            } else {
                val errorJson = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorJson, VeloraResponse::class.java)
                Resource.Error(errorResponse?.message ?: "Sunucu hatası: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("İstisna: ${e.localizedMessage}")
        }
    }

}