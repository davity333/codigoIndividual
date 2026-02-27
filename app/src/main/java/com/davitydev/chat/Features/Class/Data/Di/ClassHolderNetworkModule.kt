package com.davitydev.chat.Features.Class.Data.Di

import com.davitydev.chat.Core.di.UserRetrofit
import com.davitydev.chat.Features.Class.Data.DataSources.Api.ClassApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClassHolderNetworkModule {
    @Provides
    @Singleton
    fun provideClassApi(@UserRetrofit retrofit: Retrofit): ClassApi {
        return retrofit.create(ClassApi::class.java)
    }
}
