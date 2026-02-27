package com.davitydev.chat.Features.User.Data.Repositories

import com.davitydev.chat.Core.di.Token.TokenDataStore
import com.davitydev.chat.Features.User.Data.DataSources.Mapper.toDomain
import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserRequest
import com.davitydev.chat.Features.User.Domain.Entities.User
import com.davitydev.chat.Features.User.Domain.Repository.User_repository
import com.davitydev.chat.data.network.ApiService
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val api: ApiService,
    private val tokenDataStore: TokenDataStore
): User_repository{

    override suspend fun getAllUsers(): List<User> {
        val response = api.getAllUser()
        return response.result.map { // Corrected back to response.result
            it.toDomain()
        }
    }

    override suspend fun loginUser(request: LoginUserRequest): User {
        val loginResponse = api.login(request)
        tokenDataStore.saveIdUser(loginResponse.data.idUser)
        tokenDataStore.saveToken(loginResponse.data.token)
        loginResponse.data.attributes.username?.let { tokenDataStore.saveName(it) }
        
        return loginResponse.toDomain()
    }
}