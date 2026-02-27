package com.davitydev.chat.Core.di

import com.davitydev.chat.Core.network.ChatApi
import com.davitydev.chat.Features.Class.Data.DataSources.Api.ClassApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @UserRetrofit
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://54.88.186.16:8080/api/v1/")  // ← cambia localhost por 10.0.2.2
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideChatApi(@UserRetrofit retrofit: Retrofit): ChatApi {  // ← agrega esto
        return retrofit.create(ChatApi::class.java)
    }

    @Provides
    @Singleton
    fun provideClassApi(@UserRetrofit retrofit: Retrofit): ClassApi{
        return retrofit.create(ClassApi::class.java)
    }
}
