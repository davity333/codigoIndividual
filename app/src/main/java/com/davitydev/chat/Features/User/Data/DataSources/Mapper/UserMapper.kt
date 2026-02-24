package com.davitydev.chat.Features.User.Data.DataSources.Mapper

import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserResponse
import com.davitydev.chat.Features.User.Data.DataSources.Model.UserDto
import com.davitydev.chat.Features.User.Domain.Entities.User

fun LoginUserResponse.toDomain(): User {
    return User(
        idUser = this.data.idUser,
        username = this.data.attributes.username,
        email = this.data.attributes.email,
        password = "",
        firstname = this.data.attributes.firstName,
        lastname = this.data.attributes.lastName,
        rol = this.data.attributes.role,
        token = this.data.token
    )
}

fun UserDto.toDomain(): User {
    return User(
        idUser = this.idUser,
        username = this.attributes.username,
        email = this.attributes.Email,
        password = "",
        firstname = this.attributes.firstName,
        lastname = this.attributes.lastName,
        rol = this.attributes.role,
        token = ""
    )
}