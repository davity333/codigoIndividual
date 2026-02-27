package com.davitydev.chat.Features.Chat.Domain.Entities

data class ContactEntity(
    val id: Int,
    val userId: Int,
    val contactId: Int,
    val createdAt: String,
    val firstName: String?,
    val lastName: String?,
    val username: String?,
    val email: String?,
    val role: String?
)
