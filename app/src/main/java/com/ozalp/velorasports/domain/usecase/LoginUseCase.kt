package com.ozalp.velorasports.domain.usecase

import com.ozalp.velorasports.data.remote.dto.request.concretes.LoginUserRequest
import com.ozalp.velorasports.domain.model.Athlete
import com.ozalp.velorasports.domain.model.User
import com.ozalp.velorasports.domain.repository.UserRepository
import com.ozalp.velorasports.util.Resource
import com.ozalp.velorasports.util.handleNetworkResponse
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(loginUserRequest: LoginUserRequest): Resource<Athlete> {
        return try {
            val response = userRepository.login(loginUserRequest)

            handleNetworkResponse(response) { dto ->

                Athlete(
                    dto.coach ?: "",
                    dto.id,
                    dto.membership ?: "",
                    User(dto.user.firstName, dto.user.id, dto.user.lastName)
                )

            }
        } catch (t: Throwable) {
            Resource.Error(t.message ?: "Beklenmeyen bir hata oluştu")
        }
    }
}