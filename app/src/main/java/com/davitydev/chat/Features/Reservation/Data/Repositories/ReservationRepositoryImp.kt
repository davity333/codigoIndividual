package com.davitydev.chat.Features.Reservation.Data.Repositories

import com.davitydev.chat.Features.Reservation.Data.DataSources.Api.ReservationApi
import com.davitydev.chat.Features.Reservation.Data.DataSources.Mapper.toDomain
import com.davitydev.chat.Features.Reservation.Data.DataSources.Model.ReservationRequestDto

import com.davitydev.chat.Features.Reservation.Domain.Entities.Reservation
import com.davitydev.chat.Features.Reservation.Domain.Repository.ReservationRepository
import javax.inject.Inject

class ReservationRepositoryImp @Inject constructor(
    private val api: ReservationApi
) : ReservationRepository {

    override suspend fun createReservation(
        token: String,
        studentId: Int,
        classId: Int,
        reservationDate: String,
        attendance: Boolean
    ): Result<Reservation> {
        return try {
            val response = api.createReservation(
                token = "Bearer $token",
                body  = ReservationRequestDto(
                    studentId       = studentId,
                    classId         = classId,
                    reservationDate = reservationDate,
                    attendance      = attendance
                )
            )
            if (response.isSuccessful) {
                // Si el body es null o incompleto igual consideramos éxito
                val reservation = response.body()?.toDomain() ?: Reservation(
                    id              = 0,
                    studentId       = studentId,
                    classId         = classId,
                    reservationDate = reservationDate,
                    attendance      = attendance
                )
                Result.success(reservation)
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Error desconocido"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}