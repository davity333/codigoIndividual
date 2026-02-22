package com.davitydev.chat.Features.User.Data.Repositories

import com.davitydev.chat.Core.network.ChatApi
import com.davitydev.chat.Features.User.Data.DataSources.Mapper.toDomain
import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserRequest
import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserResponse
import com.davitydev.chat.Features.User.Domain.Entities.User
import com.davitydev.chat.Features.User.Domain.Repository.User_repository

class UserRepositoryImp(
    private val api: ChatApi
): User_repository{

    override suspend fun getAllUsers(): List<User> {
        val response = api.getAllUser()
        return response.result.map {
            it.toDomain()
        }
    }

    override suspend fun loginUser(request: LoginUserRequest): LoginUserResponse {
        return api.login(request)
    }
}