package com.davitydev.chat.Features.Chat.Data.Repository

import com.davitydev.chat.Features.Chat.Data.Model.CreateContactRequest
import com.davitydev.chat.Features.Chat.Data.Model.SendMessageRequest
import com.davitydev.chat.Features.Chat.Data.Mappers.toDomain as toChatDomain
import com.davitydev.chat.Features.Chat.Domain.Entities.ContactEntity
import com.davitydev.chat.Features.Chat.Domain.Entities.MessageEntity
import com.davitydev.chat.Features.Chat.Domain.Repository.ChatRepository
import com.davitydev.chat.Features.User.Data.DataSources.Mapper.toDomain as toUserDomain
import com.davitydev.chat.Features.User.Domain.Entities.User
import com.davitydev.chat.data.network.ApiService
import com.davitydev.chat.data.network.ChatWebSocketManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val webSocketManager: ChatWebSocketManager
) : ChatRepository {

    override suspend fun sendMessage(senderId: Int, receiverId: Int, content: String): Result<MessageEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val request =
                    SendMessageRequest(senderId, receiverId, content, System.currentTimeMillis())
                val response = apiService.sendMessage(request)
                Result.success(response.data.toChatDomain())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun getMessages(senderId: Int, receiverId: Int): Result<List<MessageEntity>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getMessages(senderId, receiverId)
                Result.success(response.messages.map { it.toChatDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override fun subscribeToMessages(userId: Int): Flow<MessageEntity> {
        return webSocketManager.startConnection(userId)
    }

    override suspend fun addContact(userId: Int, contactId: Int): Result<ContactEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val request = CreateContactRequest(userId, contactId)
                val response = apiService.addContact(request)
                Result.success(response.contact.toChatDomain())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun getContacts(userId: Int): Result<List<ContactEntity>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getContacts(userId)
                Result.success(response.contacts.map { it.toChatDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun deleteContact(userId: Int, contactId: Int): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                apiService.deleteContact(userId, contactId)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun searchUser(username: String): Result<List<User>> {
        return withContext(Dispatchers.IO) {
            try {
                val userResponses = apiService.searchUser(username)
                Result.success(userResponses.map { it.toUserDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}