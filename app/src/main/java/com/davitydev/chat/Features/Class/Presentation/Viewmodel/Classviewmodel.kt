package com.davitydev.chat.Features.Class.Presentation.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davitydev.chat.Core.di.Token.TokenDataStore
import com.davitydev.chat.Features.Class.Domain.Entities.Class
import com.davitydev.chat.Features.Class.Domain.UseCases.GetClassesByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassViewModel @Inject constructor(
    private val getClassesByDateUseCase: GetClassesByDateUseCase,
    private val tokenDataStore         : TokenDataStore
) : ViewModel() {

    private val _classes      = MutableStateFlow<List<Class>>(emptyList())
    val classes = _classes.asStateFlow()

    private val _isLoading    = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    // Llama esto cada vez que el usuario selecciona una fecha
    fun loadClassesByDate(date: String) {
        if (date.isEmpty()) return
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            val token = tokenDataStore.getToken().first() ?: ""
            getClassesByDateUseCase(token, date)
                .onSuccess { list ->
                    _classes.value = list
                }
                .onFailure { e ->
                    _errorMessage.value = "Error: ${e.message}"
                    _classes.value = emptyList()
                }
            _isLoading.value = false
        }
    }
}