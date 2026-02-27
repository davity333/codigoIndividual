package com.davitydev.chat.Features.Class.Domain.Repository

import com.davitydev.chat.Features.Class.Domain.Entities.Class

interface Class_repository {
    suspend fun getClassesByDate(token: String, date: String): Result<List<Class>>
}