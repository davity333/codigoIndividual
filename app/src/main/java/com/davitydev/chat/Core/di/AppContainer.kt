package com.davitydev.chat.Core.di

import android.content.Context
import com.davitydev.chat.Core.network.ChatApi
import com.davitydev.chat.Features.User.Data.Repositories.UserRepositoryImp
import com.davitydev.chat.Features.User.Domain.Repository.User_repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8080/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val chatApi: ChatApi by lazy {
        retrofit.create(ChatApi::class.java)
    }

    val userRepository: User_repository by lazy{
        UserRepositoryImp(chatApi)
    }
}