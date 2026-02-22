package com.davitydev.chat.Features.User.Domain.Repository


import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserRequest
import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserResponse
import com.davitydev.chat.Features.User.Domain.Entities.User
import retrofit2.http.Body

interface User_repository {
    suspend fun getAllUsers(): List<User>;
    suspend fun loginUser(request: LoginUserRequest): LoginUserResponse
}