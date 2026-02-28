package com.davitydev.chat.Features.Chat.Data.Model

import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("contactId") val contactId: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("role") val role: String?
)
