package com.davitydev.chat.Features.Chat.Data.Mappers

import com.davitydev.chat.Features.Chat.Domain.Entities.MessageEntity
import com.davitydev.chat.Features.Chat.Data.Model.Message

fun Message.toDomain() = MessageEntity(
    id = id,
    senderId = senderId,
    receiverId = receiverId,
    content = content
)
