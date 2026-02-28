package com.davitydev.chat.Features.Chat.Data.Model

import com.google.gson.annotations.SerializedName

data class UserAttributes(
    @SerializedName("username") val username: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("role") val role: String?
)