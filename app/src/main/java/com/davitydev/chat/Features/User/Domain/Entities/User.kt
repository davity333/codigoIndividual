package com.davitydev.chat.Features.User.Domain.Entities

data class User (
    val idUser: Int,
    val username: String,
    val email: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val rol: String,
    val token: String
)