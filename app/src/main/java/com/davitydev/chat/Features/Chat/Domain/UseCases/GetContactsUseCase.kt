package com.davitydev.chat.Features.Chat.Domain.UseCases

import com.davitydev.chat.Features.Chat.Domain.Entities.ContactEntity
import com.davitydev.chat.Features.Chat.Domain.Repository.ChatRepository
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(userId: Int): Result<List<ContactEntity>> {
        return repository.getContacts(userId)
    }
}