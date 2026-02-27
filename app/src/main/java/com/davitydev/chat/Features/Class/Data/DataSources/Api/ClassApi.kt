package com.davitydev.chat.Features.Class.Data.DataSources.Api

import com.davitydev.chat.Features.Class.Data.DataSources.Model.ClassByDateResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ClassApi {
    @GET("classes/date/{date}")
    suspend fun getClassesByDate(
        @Header("Authorization") token: String,
        @Path("date") date: String
    ): Response<ClassByDateResponseDto>
}