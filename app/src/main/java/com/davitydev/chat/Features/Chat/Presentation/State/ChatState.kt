package com.davitydev.chat.Features.Chat.Presentation.State

import com.davitydev.chat.Features.Chat.Domain.Entities.ContactEntity
import com.davitydev.chat.Features.Chat.Domain.Entities.MessageEntity
import com.davitydev.chat.Features.User.Domain.Entities.User

data class ChatState(
    val messages: List<MessageEntity> = emptyList(),
    val contacts: List<ContactEntity> = emptyList(),
    val searchedUsers: List<User> = emptyList(),
    val currentUserId: Int? = null,
    val chatPartner: ContactEntity? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val messageText: String = "",
    val searchText: String = "" // State for the search input field
)
