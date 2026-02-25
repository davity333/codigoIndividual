package com.davitydev.chat.Features.Login.Presentation.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davitydev.chat.Core.di.Token.TokenDataStore
import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserRequest
import com.davitydev.chat.Features.User.Domain.UseCases.LoginUserUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUsecase: LoginUserUsecase,
    private val tokenDataStore: TokenDataStore
) : ViewModel() {
    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome = _navigateToHome.asStateFlow()
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
                    tokenDataStore.saveName("${user.firstname} ${user.lastname}")
                    tokenDataStore.saveIdUser(user.idUser)
                    _loginSuccess.value = true
                    delay(1000)              // ← espera 1 segundo
                    _navigateToHome.value = true
                },
                onFailure = { exception ->
                    _error.value = getErrorMessage(exception)
                }
            )
            _loading.value = false
        }
    }

    private fun getErrorMessage(exception: Throwable): String {
        if (exception is retrofit2.HttpException) {
            val errorBody = exception.response()?.errorBody()?.string()
            if (errorBody != null) {
                return try {
                    val json = org.json.JSONObject(errorBody)
                    json.getString("Detail")  // ← saca el "Detail" del JSON
                } catch (e: Exception) {
                    "Error al iniciar sesión"
                }
            }
        }
        return when {
            exception.message?.contains("Unable to resolve host") == true -> "Sin conexión a internet"
            else -> "Error al iniciar sesión"
        }
    }
}