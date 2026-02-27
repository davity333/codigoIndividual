package com.davitydev.chat.Features.Class.Data.DataSources.Mapper

import com.davitydev.chat.Features.Class.Data.DataSources.Model.ClassDto
import com.davitydev.chat.Features.Class.Domain.Entities.Class

fun ClassDto.toDomain() = Class(
    id          = id,
    teacherId   = teacherId,
    title       = title,
    description = description,
    classDate   = classDate,
    startTime   = startTime,
    endTime     = endTime,
    capacity    = capacity,
    status      = status,
    firstName   = firstName,
    lastName    = lastName
)