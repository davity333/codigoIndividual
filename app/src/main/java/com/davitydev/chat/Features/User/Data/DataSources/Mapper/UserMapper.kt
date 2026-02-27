package com.davitydev.chat.Features.User.Data.DataSources.Mapper

import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserResponse
import com.davitydev.chat.Features.User.Data.DataSources.Model.UserDto
import com.davitydev.chat.Features.User.Data.DataSources.Model.UserResponse
import com.davitydev.chat.Features.User.Domain.Entities.User
import com.davitydev.chat.Features.Chat.Data.Model.UserSearchResponse

fun UserSearchResponse.toDomain(): User {
    return User(
        idUser = this.idUser,
        username = this.attributes.username,
        email = this.attributes.email ?: "No email",
        firstname = this.attributes.firstName ?: "No firstname",
        lastname = this.attributes.lastName ?: "No lastname",
        rol = this.attributes.role ?: "No rol",
        token = ""
    )
}

fun UserDto.toDomain(): User {
    return User(
        idUser = this.idUser,
        username = this.attributes.username,
        email = this.attributes.email,
        firstname= this.attributes.firstName,
        lastname = this.attributes.lastName,
        rol = this.attributes.role,
        token = ""
    )
}

fun LoginUserResponse.toDomain(): User{
    return User(
        idUser = this.data.idUser,
        username = this.data.attributes.username,
        email = this.data.attributes.email,
        firstname = this.data.attributes.firstName,
        lastname = this.data.attributes.lastName,
        rol = this.data.attributes.role,
        token = this.data.token
    )
}
