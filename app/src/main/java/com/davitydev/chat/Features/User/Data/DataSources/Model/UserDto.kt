package com.davitydev.chat.Features.User.Data.DataSources.Model

data class LoginUserResponse(
    val data: LoginUserData
)

data class UserResponse(
    val result: List<UserDto>
)

data class LoginUserData(
    val attributes: LoginUserAttributes,
    val idUser: Int,
    val token: String,
)

data class LoginUserAttributes(
    val email: String,
    val firstName: String,
    val lastName: String,
    val role: String,
    val username: String
)

data class LoginUserRequest(
    val email: String,
    val password: String
)

data class UserAttributes(
    val Email: String,
    val firstName: String,
    val lastName: String,
    val role: String,
    val username: String
)

data class UserDto(
    val attributes: UserAttributes,
    val idUser: Int,
)

data class ErrorResponse(
    val Detail: String,
    val error: String
)