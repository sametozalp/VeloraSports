package com.ozalp.velorasports.domain.usecase

import com.ozalp.velorasports.data.remote.dto.request.concretes.CreateUserRequest
import com.ozalp.velorasports.data.remote.dto.response.abstracts.VeloraResponse
import com.ozalp.velorasports.data.remote.dto.response.concretes.UserResponse
import com.ozalp.velorasports.domain.model.User
import com.ozalp.velorasports.domain.repository.UserRepository
import com.ozalp.velorasports.util.Resource
import com.ozalp.velorasports.util.handleNetworkResponse
import retrofit2.Response
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(createUserRequest: CreateUserRequest): Resource<User> {
        return try {
            val response: Response<VeloraResponse<UserResponse>> =
                userRepository.getUser(createUserRequest)
            handleNetworkResponse(response) { dto ->
                User(
                    firstName = dto.firstName,
                    id = dto.id,
                    lastName = dto.lastName
                )
            }
        } catch (t: Throwable) {
            Resource.Error(t.message ?: "Beklenmeyen bir hata oluştu")
        }
    }
}