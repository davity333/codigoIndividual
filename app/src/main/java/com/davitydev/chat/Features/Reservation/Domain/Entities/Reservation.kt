package com.davitydev.chat.Features.Reservation.Domain.Entities

data class Reservation(
    val id: Int,
    val studentId: Int,
    val classId: Int,
    val reservationDate: String,
    val attendance: Boolean
)