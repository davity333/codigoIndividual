package com.davitydev.chat.Features.Login.Presentation.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davitydev.chat.Features.User.Domain.UseCases.LoginUserUsecase

class LoginViewModelFactory(
    private val usecase: LoginUserUsecase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(usecase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
