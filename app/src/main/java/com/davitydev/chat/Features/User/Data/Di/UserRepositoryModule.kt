package com.davitydev.chat.Features.User.Data.Di

import com.davitydev.chat.Features.User.Data.Repositories.UserRepositoryImp
import com.davitydev.chat.Features.User.Domain.Repository.User_repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {
    @Binds
    abstract fun bindUsersRepository(
        userRepositoryImp: UserRepositoryImp
    ): User_repository
}