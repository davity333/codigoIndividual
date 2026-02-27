package com.davitydev.chat.Features.Chat.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davitydev.chat.Core.di.Token.TokenDataStore
import com.davitydev.chat.Features.Chat.Domain.UseCases.AddContactUseCase
import com.davitydev.chat.Features.Chat.Domain.UseCases.GetContactsUseCase
import com.davitydev.chat.Features.Chat.Domain.UseCases.SearchUserUseCase
import com.davitydev.chat.Features.Chat.Presentation.State.ContactsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val addContactUseCase: AddContactUseCase,
    private val getContactsUseCase: GetContactsUseCase,
    private val searchUserUseCase: SearchUserUseCase,
    private val tokenDataStore: TokenDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContactsState())
    val uiState: StateFlow<ContactsState> = _uiState.asStateFlow()

    init {
        getContacts()
    }

    fun onSearchTextChange(newText: String) {
        _uiState.update { it.copy(searchText = newText) }
    }

    fun getContacts(): Job {
        return viewModelScope.launch {
            val userId = tokenDataStore.getIdUser().firstOrNull()
            if (userId == null) {
                _uiState.update { it.copy(error = "User not logged in") }
                return@launch
            }

            _uiState.update { it.copy(isLoading = true) }
            getContactsUseCase(userId)
                .onSuccess {
                    _uiState.update { currentState ->
                        currentState.copy(contacts = it, isLoading = false)
                    }
                }
                .onFailure {
                    _uiState.update { currentState ->
                        currentState.copy(error = it.message, isLoading = false)
                    }
                }
        }
    }

    fun addContact(contactId: Int) {
        viewModelScope.launch {
            val userId = tokenDataStore.getIdUser().firstOrNull() ?: return@launch
            addContactUseCase(userId, contactId)
                .onSuccess { newContact ->
                    _uiState.update { currentState ->
                        currentState.copy(contacts = currentState.contacts + newContact)
                    }
                }
                .onFailure {
                    _uiState.update { currentState ->
                        currentState.copy(error = it.message)
                    }
                }
        }
    }

    fun searchUser() {
        viewModelScope.launch {
            val username = _uiState.value.searchText
            if (username.isBlank()) return@launch

            _uiState.update { it.copy(isLoading = true, searchedUsers = emptyList()) }
            searchUserUseCase(username)
                .onSuccess {
                    _uiState.update { currentState ->
                        currentState.copy(searchedUsers = it, isLoading = false)
                    }
                }
                .onFailure {
                    _uiState.update { currentState ->
                        currentState.copy(error = it.message, isLoading = false, searchedUsers = emptyList())
                    }
                }
        }
    }
}