package com.davitydev.chat.Features.Chat.Domain.Entities

data class MessageEntity(
    val id: Int,
    val senderId: Int,
    val receiverId: Int,
    val content: String
)
