package com.davitydev.chat.Features.Reservation.Data.DataSources.Api


import com.davitydev.chat.Features.Reservation.Data.DataSources.Model.ReservationRequestDto
import com.davitydev.chat.Features.Reservation.Data.DataSources.Model.ReservationResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ReservationApi {
    @POST("reservations/create")
    suspend fun createReservation(
        @Header("Authorization") token: String,
        @Body body: ReservationRequestDto
    ): Response<ReservationResponseDto>
}