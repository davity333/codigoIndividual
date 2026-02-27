package com.davitydev.chat.Core.di

import com.davitydev.chat.Core.navigation.FeatureNavGraph
import com.davitydev.chat.Core.navigation.HomeNavGraph
import com.davitydev.chat.Features.Home.Presentation.Navigation.HomeNavGraphImpl
import com.davitydev.chat.Features.Login.Presentation.Navigation.LoginNavGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideLoginNavGraph(): FeatureNavGraph = LoginNavGraph()

    @Provides
    @Singleton
    fun provideHomeNavGraph(): HomeNavGraph = HomeNavGraphImpl()
}
