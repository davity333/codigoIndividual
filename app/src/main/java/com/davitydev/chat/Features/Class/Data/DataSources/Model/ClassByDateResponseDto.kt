package com.davitydev.chat.Features.Class.Data.DataSources.Model

data class ClassByDateResponseDto(
    val classes: List<ClassDto>
)

data class ClassDto(
    val id         : Int,
    val teacherId  : Int,
    val title      : String,
    val description: String,
    val classDate  : String,
    val startTime  : String,
    val endTime    : String,
    val capacity   : Int,
    val status     : String,
    val firstName  : String,
    val lastName   : String
)