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

    suspend operator fun invoke(createUserRequest: CreateUserRequest): Resource<User> {
        val response: Response<VeloraResponse<UserResponse>> =
            userRepository.getUser(createUserRequest)

        if (!response.isSuccessful) {
            val errorJson = response.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorJson, VeloraResponse::class.java)
            return Resource.Error(errorResponse?.message ?: "Sunucu hatası: ${response.code()}")
        }

        val body = response.body()
        if (body == null || body.data == null || !body.success) {
            if (body?.message != null && !body.message.equals(""))
                return Resource.Error(body.message)
            else
                return Resource.Error("Boş veya başarısız response")
        }

        val userResponse = body.data
        val user = User(
            firstName = userResponse.firstName,
            id = userResponse.id,
            lastName = userResponse.lastName
        )

        return Resource.Success(user)
    }
}