package com.davitydev.chat.Core.network

import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserRequest
import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserResponse
import com.davitydev.chat.Features.User.Data.DataSources.Model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatApi{
    @GET("/getAll")
    suspend fun getAllUser(): UserResponse

    @POST("login")
    suspend fun login(@Body request: LoginUserRequest): LoginUserResponse
}