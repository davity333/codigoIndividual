package com.davitydev.chat.Features.Login.Presentation.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davitydev.chat.Core.di.Token.TokenDataStore
import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserRequest
import com.davitydev.chat.Features.User.Domain.UseCases.LoginUserUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUsecase: LoginUserUsecase,
    private val tokenDataStore: TokenDataStore
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess = _loginSuccess.asStateFlow()

    fun onEmailChange(email: String) { _email.value = email }
    fun onPasswordChange(pass: String) { _password.value = pass }

    fun login() {
        if (_email.value.isBlank() || _password.value.isBlank()) {
            _error.value = "Por favor completa todos los campos"
            return
        }

        viewModelScope.launch {
            _loading.value = true
            _error.value = ""
            _loginSuccess.value = false

            val request = LoginUserRequest(
                email = _email.value,
                password = _password.value
            )

            val result = loginUserUsecase(request)

            result.fold(
                onSuccess = { user ->
                    tokenDataStore.saveToken(user.token)
                    _loginSuccess.value = true
                },
                onFailure = { exception ->
                    _error.value = exception.message ?: "Error al iniciar sesión"
                }
            )
            _loading.value = false
        }
    }
}