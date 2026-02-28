package com.davitydev.chat.Features.Chat.Domain.UseCases

import com.davitydev.chat.Features.Chat.Domain.Repository.ChatRepository
import javax.inject.Inject

class SubscribeToMessagesUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke(userId: Int) = repository.subscribeToMessages(userId)
}
