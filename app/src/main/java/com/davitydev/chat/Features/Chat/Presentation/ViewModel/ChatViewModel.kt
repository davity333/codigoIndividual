package com.davitydev.chat.Features.Chat.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davitydev.chat.Core.di.Token.TokenDataStore
import com.davitydev.chat.Features.Chat.Domain.UseCases.GetContactsUseCase
import com.davitydev.chat.Features.Chat.Domain.UseCases.GetMessagesUseCase
import com.davitydev.chat.Features.Chat.Domain.UseCases.SendMessageUseCase
import com.davitydev.chat.Features.Chat.Domain.UseCases.SubscribeToMessagesUseCase
import com.davitydev.chat.Features.Chat.Presentation.State.ChatState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val subscribeToMessagesUseCase: SubscribeToMessagesUseCase,
    private val getContactsUseCase: GetContactsUseCase,
    private val tokenDataStore: TokenDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatState())
    val uiState: StateFlow<ChatState> = _uiState.asStateFlow()

    private val initializationJob: Job

    init {
        initializationJob = viewModelScope.launch {
            _uiState.update { it.copy(currentUserId = tokenDataStore.getIdUser().firstOrNull()) }
        }
    }

    fun initializeChat(contactId: Int) {
        viewModelScope.launch {
            initializationJob.join()
            getContacts().join()
            loadChatPartner(contactId)
            getMessages(contactId)
            subscribeToMessages(contactId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        // The WebSocket connection is now managed by the repository and its lifecycle
    }

    fun onMessageChange(newText: String) {
        _uiState.update { it.copy(messageText = newText) }
    }

    private fun loadChatPartner(contactId: Int) {
        val partner = _uiState.value.contacts.find { contact -> contact.contactId == contactId }
        _uiState.update { state -> state.copy(chatPartner = partner) }
    }

    private fun getMessages(contactId: Int) {
        viewModelScope.launch {
            val userId = _uiState.value.currentUserId ?: return@launch
            getMessagesUseCase(senderId = userId, receiverId = contactId)
                .onSuccess { messages ->
                    _uiState.update { currentState ->
                        currentState.copy(messages = messages)
                    }
                }
        }
    }

    fun sendMessage(receiverId: Int) {
        viewModelScope.launch {
            val senderId = _uiState.value.currentUserId ?: return@launch
            val content = _uiState.value.messageText
            if (content.isBlank()) return@launch

            sendMessageUseCase(senderId, receiverId, content)
                .onSuccess { sentMessage ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            messages = currentState.messages + sentMessage,
                            messageText = ""
                        )
                    }
                }
        }
    }

    private fun subscribeToMessages(contactId: Int) {
        viewModelScope.launch {
            val userId = _uiState.value.currentUserId ?: return@launch
            subscribeToMessagesUseCase(userId)
                .onEach { message ->
                    val isRelevant = (message.senderId == userId && message.receiverId == contactId) || (message.senderId == contactId && message.receiverId == userId)
                    if (isRelevant) {
                        _uiState.update { currentState ->
                            currentState.copy(messages = currentState.messages + message)
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun getContacts(): Job {
        return viewModelScope.launch {
            val userId = _uiState.value.currentUserId ?: return@launch
            if (_uiState.value.contacts.isEmpty()) { // Only load if not already loaded
                getContactsUseCase(userId).onSuccess {
                    _uiState.update { currentState ->
                        currentState.copy(contacts = it)
                    }
                }
            }
        }
    }
}