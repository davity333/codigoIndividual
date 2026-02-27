package com.davitydev.chat.Features.Chat.Domain.UseCases

import com.davitydev.chat.Features.Chat.Domain.Repository.ChatRepository
import com.davitydev.chat.Features.User.Domain.Entities.User
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(username: String): Result<List<User>> {
        return repository.searchUser(username)
    }
}
