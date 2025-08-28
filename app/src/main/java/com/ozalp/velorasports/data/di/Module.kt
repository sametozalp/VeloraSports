package com.ozalp.velorasports.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ozalp.velorasports.data.remote.api.UserAPI
import com.ozalp.velorasports.data.repository.UserRepositoryImpl
import com.ozalp.velorasports.domain.repository.UserRepository
import com.ozalp.velorasports.domain.usecase.GetUserUseCase
import com.ozalp.velorasports.presentation.viewmodel.RegisterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserAPI(retrofit: Retrofit): UserAPI {
        return retrofit.create(UserAPI::class.java)
    }

    @Provides
    @Singleton
    fun userRepository(userAPI: UserAPI): UserRepository {
        return UserRepositoryImpl(userAPI)
    }

    @Provides
    @Singleton
    fun provideUserUseCase(userRepository: UserRepository): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }
}