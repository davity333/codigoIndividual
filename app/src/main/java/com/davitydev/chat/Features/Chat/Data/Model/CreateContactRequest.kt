package com.davitydev.chat.Features.Chat.Data.Model

import com.google.gson.annotations.SerializedName

data class CreateContactRequest(
    @SerializedName("userId") val userId: Int,
    @SerializedName("contactId") val contactId: Int
)