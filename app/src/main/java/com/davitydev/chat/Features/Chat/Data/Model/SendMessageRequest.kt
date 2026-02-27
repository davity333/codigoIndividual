package com.davitydev.chat.Features.Chat.Data.Model

import com.google.gson.annotations.SerializedName

data class SendMessageRequest(
    @SerializedName("senderId") val senderId: Int,
    @SerializedName("receiverId") val receiverId: Int,
    @SerializedName("content") val content: String,
    @SerializedName("timeMessage") val timeMessage: Long
)