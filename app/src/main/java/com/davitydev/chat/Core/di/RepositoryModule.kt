package com.davitydev.chat.Core.di

import com.davitydev.chat.Features.Chat.Data.Repository.ChatRepositoryImpl
import com.davitydev.chat.Features.Chat.Domain.Repository.ChatRepository
import com.davitydev.chat.Features.User.Data.Repositories.UserRepositoryImp
import com.davitydev.chat.Features.User.Domain.Repository.User_repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindChatRepository(impl: ChatRepositoryImpl): ChatRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImp): User_repository
}
