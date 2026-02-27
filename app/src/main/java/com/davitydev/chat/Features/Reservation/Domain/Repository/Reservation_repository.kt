package com.davitydev.chat.Features.Reservation.Domain.Repository

import com.davitydev.chat.Features.Reservation.Domain.Entities.Reservation

interface ReservationRepository {
    suspend fun createReservation(
        token: String,
        studentId: Int,
        classId: Int,
        reservationDate: String,
        attendance: Boolean
    ): Result<Reservation>
}