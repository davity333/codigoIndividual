package com.davitydev.chat.Features.Class.Domain.Entities

data class Class(
    val id: Int,
    val teacherId: Int,
    val title: String,
    val description: String,
    val classDate: String,
    val startTime: String,
    val endTime: String,
    val capacity: Int,
    val status: String,
    val firstName: String,
    val lastName: String
)