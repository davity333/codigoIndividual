package com.davitydev.chat.Features.Chat.Data.Mappers

import com.davitydev.chat.Features.Chat.Data.Model.Contact
import com.davitydev.chat.Features.Chat.Domain.Entities.ContactEntity

fun Contact.toDomain(): ContactEntity {
    return ContactEntity(
        id = this.id,
        userId = this.userId,
        contactId = this.contactId,
        createdAt = this.createdAt,
        firstName = this.firstName,
        lastName = this.lastName,
        username = this.username,
        email = this.email,
        role = this.role
    )
}
