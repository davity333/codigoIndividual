package com.davitydev.chat.Features.Chat.Presentation.State

import com.davitydev.chat.Features.Chat.Domain.Entities.ContactEntity
import com.davitydev.chat.Features.User.Domain.Entities.User

data class ContactsState(
    val contacts: List<ContactEntity> = emptyList(),
    val searchedUsers: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchText: String = ""
)