package com.davitydev.chat.Features.Chat.Domain.Repository

import com.davitydev.chat.Features.Chat.Domain.Entities.ContactEntity
import com.davitydev.chat.Features.Chat.Domain.Entities.MessageEntity
import com.davitydev.chat.Features.User.Domain.Entities.User
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun sendMessage(senderId: Int, receiverId: Int, content: String): Result<MessageEntity>
    suspend fun getMessages(senderId: Int, receiverId: Int): Result<List<MessageEntity>>
    fun subscribeToMessages(userId: Int): Flow<MessageEntity>
    suspend fun addContact(userId: Int, contactId: Int): Result<ContactEntity> // Changed to return ContactEntity
    suspend fun getContacts(userId: Int): Result<List<ContactEntity>>
    suspend fun deleteContact(userId: Int, contactId: Int): Result<Unit>
    suspend fun searchUser(username: String): Result<List<User>>
}