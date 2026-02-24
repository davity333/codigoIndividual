package com.davitydev.chat.Features.User.Domain.UseCases

import com.davitydev.chat.Features.User.Domain.Entities.User
import com.davitydev.chat.Features.User.Domain.Repository.User_repository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val repository: User_repository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return try {
            val users = repository.getAllUsers()
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}