package com.davitydev.chat.data.network

import com.davitydev.chat.Features.Chat.Data.Model.AddContactResponse
import com.davitydev.chat.Features.Chat.Data.Model.ContactsResponse
import com.davitydev.chat.Features.Chat.Data.Model.CreateContactRequest
import com.davitydev.chat.Features.Chat.Data.Model.MessageHistoryResponse
import com.davitydev.chat.Features.Chat.Data.Model.SendMessageResponse
import com.davitydev.chat.Features.Chat.Data.Model.UserSearchResponse
import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserRequest
import com.davitydev.chat.Features.User.Data.DataSources.Model.LoginUserResponse
import com.davitydev.chat.Features.User.Data.DataSources.Model.UserResponse
import com.davitydev.chat.Features.Chat.Data.Model.SendMessageRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("users/login")
    suspend fun login(@Body request: LoginUserRequest): LoginUserResponse

    @GET("users/{username}")
    suspend fun searchUser(@Path("username") username: String): List<UserSearchResponse>

    @GET("contacts/getAll/{userId}")
    suspend fun getContacts(@Path("userId") userId: Int): ContactsResponse

    @POST("contacts/create")
    suspend fun addContact(@Body request: CreateContactRequest): AddContactResponse

    @DELETE("contacts/{userId}/{contactId}")
    suspend fun deleteContact(@Path("userId") userId: Int, @Path("contactId") contactId: Int)

    @GET("message/getAll")
    suspend fun getMessages(
        @Query("senderId") senderId: Int,
        @Query("receiveId") receiveId: Int
    ): MessageHistoryResponse

    @POST("message/send")
    suspend fun sendMessage(@Body request: SendMessageRequest): SendMessageResponse

    @GET("getAll")
    suspend fun getAllUser(): UserResponse
}