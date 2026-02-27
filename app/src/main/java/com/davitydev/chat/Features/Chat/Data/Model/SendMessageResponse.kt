package com.davitydev.chat.Features.Chat.Data.Model

import com.google.gson.annotations.SerializedName

data class SendMessageResponse(
    @SerializedName("data") val data: Message
)