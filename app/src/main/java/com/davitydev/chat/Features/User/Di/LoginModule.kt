package com.davitydev.chat.Features.User.Di

import com.davitydev.chat.Core.di.AppContainer
import com.davitydev.chat.Features.Login.Presentation.Viewmodel.LoginViewModelFactory
import com.davitydev.chat.Features.User.Domain.UseCases.LoginUserUsecase

class LoginModule(private val appContainer: AppContainer) {

    private fun provideLoginUserUseCase(): LoginUserUsecase{
        return LoginUserUsecase(appContainer.userRepository)
    }

    fun provideUsersViewModelFactory():  LoginViewModelFactory{
        return LoginViewModelFactory(
            usecase = provideLoginUserUseCase()
        )
    }
}