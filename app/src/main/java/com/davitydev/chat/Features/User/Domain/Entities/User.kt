package com.davitydev.chat.Features.User.Domain.Entities

data class User (
    val idUser: Int,
    val username: String,
    val email: String,
    val password: String,
)