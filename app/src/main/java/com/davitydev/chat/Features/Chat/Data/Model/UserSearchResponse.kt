package com.davitydev.chat.Features.Chat.Data.Model

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(
    @SerializedName("type") val type: String,
    @SerializedName("idUser") val idUser: Int,
    @SerializedName("attributes") val attributes: UserAttributes
)