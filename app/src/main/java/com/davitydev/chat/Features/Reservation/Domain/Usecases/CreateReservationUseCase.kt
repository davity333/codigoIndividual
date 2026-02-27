package com.davitydev.chat.Features.Reservation.Domain.Usecases

import com.davitydev.chat.Features.Reservation.Domain.Entities.Reservation
import com.davitydev.chat.Features.Reservation.Domain.Repository.ReservationRepository
import javax.inject.Inject

class CreateReservationUseCase @Inject constructor(
    private val repository: ReservationRepository
) {
    suspend operator fun invoke(
        token: String,
        studentId: Int,
        classId: Int,
        reservationDate: String,
        attendance: Boolean = true
    ): Result<Reservation> {
        return repository.createReservation(
            token           = token,
            studentId       = studentId,
            classId         = classId,
            reservationDate = reservationDate,
            attendance      = attendance
        )
    }
}