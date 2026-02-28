package com.davitydev.chat.Features.Chat.Domain.UseCases

import com.davitydev.chat.Features.Chat.Domain.Entities.MessageEntity
import com.davitydev.chat.Features.Chat.Domain.Repository.ChatRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(senderId: Int, receiverId: Int, content: String): Result<MessageEntity> {
        return repository.sendMessage(senderId, receiverId, content)
    }
}