package com.davitydev.chat.Features.Reservation.Data.DataSources.Model

data class ReservationRequestDto(
    val studentId: Int,
    val classId: Int,
    val reservationDate: String,
    val attendance: Boolean
)

data class ReservationResponseDto(
    val id             : Int?     = null,
    val studentId      : Int?     = null,
    val classId        : Int?     = null,
    val reservationDate: String?  = null,
    val attendance     : Boolean? = null
)