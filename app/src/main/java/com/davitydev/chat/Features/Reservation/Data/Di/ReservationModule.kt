package com.davitydev.chat.Features.Reservation.Data.Di

import com.davitydev.chat.Core.di.UserRetrofit
import com.davitydev.chat.Features.Reservation.Data.DataSources.Api.ReservationApi
import com.davitydev.chat.Features.Reservation.Data.Repositories.ReservationRepositoryImp
import com.davitydev.chat.Features.Reservation.Domain.Repository.ReservationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReservationNetworkModule {
    @Provides
    @Singleton
    fun provideReservationApi(@UserRetrofit retrofit: Retrofit): ReservationApi {
        return retrofit.create(ReservationApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ReservationModule {
    @Binds
    abstract fun bindReservationRepository(
        imp: ReservationRepositoryImp
    ): ReservationRepository
}