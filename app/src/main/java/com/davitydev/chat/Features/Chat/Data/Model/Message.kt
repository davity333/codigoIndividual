package com.davitydev.chat.Features.Chat.Data.Model

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName(value = "idMessage", alternate = ["id"]) val id: Int,
    @SerializedName(value = "senderId", alternate = ["sender_id"]) val senderId: Int,
    @SerializedName(value = "receiveId", alternate = ["receiver_id"]) val receiverId: Int,
    @SerializedName("content") val content: String,
    @SerializedName(value = "timeMessage", alternate = ["created_at"]) val timestamp: String
)