package com.davitydev.chat.Features.Chat.Data.Model

import com.google.gson.annotations.SerializedName

data class ContactsResponse(
    @SerializedName("contacts") val contacts: List<Contact>,
    @SerializedName("message") val message: String
)
