package com.davitydev.chat.Features.Chat.Data.Model

import com.google.gson.annotations.SerializedName

data class AddContactResponse(
    @SerializedName("message") val message: String,
    @SerializedName("contact") val contact: Contact
)
