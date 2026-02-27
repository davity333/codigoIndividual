package com.davitydev.chat.Features.Class.Domain.UseCases

import com.davitydev.chat.Features.Class.Domain.Entities.Class
import com.davitydev.chat.Features.Class.Domain.Repository.Class_repository
import javax.inject.Inject

class GetClassesByDateUseCase @Inject constructor(
    private val repository: Class_repository
) {
    suspend operator fun invoke(token: String, date: String): Result<List<Class>> {
        return repository.getClassesByDate(token, date)
    }
}