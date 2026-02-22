package com.davitydev.chat.Features.User.Domain.UseCases

import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserRequest
import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserResponse
import com.davitydev.chat.Features.User.Domain.Entities.User
import com.davitydev.chat.Features.User.Domain.Repository.User_repository

class LoginUserUsecase(
    private val repository: User_repository
) {

    suspend operator fun invoke(request: LoginUserRequest): Result<LoginUserResponse> {
        return try {
            val response = repository.loginUser(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
