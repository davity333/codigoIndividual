package com.davitydev.chat.Features.Class.Data.Repositories

import com.davitydev.chat.Features.Class.Data.DataSources.Api.ClassApi
import com.davitydev.chat.Features.Class.Data.DataSources.Mapper.toDomain
import com.davitydev.chat.Features.Class.Domain.Entities.Class
import com.davitydev.chat.Features.Class.Domain.Repository.Class_repository
import javax.inject.Inject

class ClassRepositoryImp @Inject constructor(
    private val api: ClassApi
) : Class_repository {

    override suspend fun getClassesByDate(token: String, date: String): Result<List<Class>> {
        return try {
            val response = api.getClassesByDate(
                token = "Bearer $token",
                date  = date
            )
            if (response.isSuccessful) {
                val classes = response.body()?.classes?.map { it.toDomain() } ?: emptyList()
                Result.success(classes)
            } else {
                Result.failure(Exception("Error al obtener clases: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}