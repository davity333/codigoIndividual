package com.davitydev.chat.Features.User.Data.DataSources.Model

import com.davitydev.chat.Features.User.Domain.Entities.User

data class UserResponse(
    val result: List<UserDto>
)

data class UserDto(
    val idUser: Int,
    val username: String,
    val email: String,
    val password: String,
)

data class CreateUserRequest(
    val username: String,
    val email: String,
    val password: String
)

data class LoginUserRequest(
    val email: String,
    val password: String
)

data class LoginUserResponse(
    val email: String,
    val username: String,
    val idUser: Int,
    val token: String,
    val type: String
)