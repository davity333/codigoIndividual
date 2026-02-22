package com.davitydev.chat.Features.User.Data.DataSources.Mapper

import com.davitydev.chat.Features.User.Data.DataSources.Model.UserDto
import com.davitydev.chat.Features.User.Domain.Entities.User

fun UserDto.toDomain(): User{
    return User(
        idUser = this.idUser,
        username = this.username,
        email = this.email,
        password = this.password
    )
}