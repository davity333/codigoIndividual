package com.davitydev.chat.Features.User.Data.DataSources.Api

import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserRequest
import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserResponse
import com.davitydev.chat.Features.User.Data.DataSources.Model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersApi {
    @POST("users/login")
    suspend fun login(@Body request: LoginUserRequest): LoginUserResponse

    @GET("users/users")
    suspend fun getAllUsers(): UserResponse
}