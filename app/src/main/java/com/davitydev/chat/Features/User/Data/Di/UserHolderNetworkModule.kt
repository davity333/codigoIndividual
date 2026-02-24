package com.davitydev.chat.Features.User.Data.Di

import com.davitydev.chat.Core.di.UserRetrofit
import com.davitydev.chat.Features.User.Data.DataSources.Api.UsersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserHolderNetworkModule {
    @Provides
    @Singleton
    fun provideUserApi(@UserRetrofit retrofit: Retrofit): UsersApi{
        return retrofit.create(UsersApi::class.java)
    }
}