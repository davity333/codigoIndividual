package com.davitydev.chat.Features.Reservation.Data.DataSources.Mapper


import com.davitydev.chat.Features.Reservation.Data.DataSources.Model.ReservationResponseDto
import com.davitydev.chat.Features.Reservation.Domain.Entities.Reservation

fun ReservationResponseDto.toDomain() = Reservation(
    id              = id              ?: 0,
    studentId       = studentId       ?: 0,
    classId         = classId         ?: 0,
    reservationDate = reservationDate ?: "",
    attendance      = attendance      ?: false
)