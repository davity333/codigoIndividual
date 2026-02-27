package com.davitydev.chat.Features.Chat.Data.Model

import com.google.gson.annotations.SerializedName

data class MessageHistoryResponse(
    @SerializedName("messages") val messages: List<Message>
)