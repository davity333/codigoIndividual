package com.davitydev.chat.Features.Chat.Domain.UseCases

import com.davitydev.chat.Features.Chat.Domain.Repository.ChatRepository
import javax.inject.Inject

class DeleteContactUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(userId: Int, contactId: Int): Result<Unit> {
        return repository.deleteContact(userId, contactId)
    }
}