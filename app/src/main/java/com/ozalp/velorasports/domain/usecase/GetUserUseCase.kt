package com.ozalp.velorasports.domain.usecase

import com.ozalp.velorasports.data.remote.dto.request.concretes.CreateUserRequest
import com.ozalp.velorasports.data.remote.dto.response.abstracts.Response
import com.ozalp.velorasports.data.remote.dto.response.concretes.UserResponse
import com.ozalp.velorasports.domain.model.User
import com.ozalp.velorasports.domain.repository.UserRepository
import com.ozalp.velorasports.util.Resource
import javax.inject.Inject

class GetUserUseCase @Inject constructor(val userRepository: UserRepository) {

    suspend operator fun invoke(createUserRequest: CreateUserRequest): Resource<User> {
        val response: Response<UserResponse>? = userRepository.getUser(createUserRequest)

        return if (response?.success == true) {
            val body = response.data
            if (body != null) {
                val user = User(
                    firstName = body.firstName,
                    id = body.id,
                    lastName = body.lastName
                )
                println(user)
                Resource.Success(user)
            } else {
                Resource.Error("Boş response body")
            }
        } else {
            Resource.Error("hata")
        }
    }

}