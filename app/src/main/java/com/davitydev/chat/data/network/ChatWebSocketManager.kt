package com.davitydev.chat.data.network

import com.davitydev.chat.Features.Chat.Data.Mappers.toDomain
import com.davitydev.chat.Features.Chat.Domain.Entities.MessageEntity
import com.davitydev.chat.Features.Chat.Data.Model.Message
import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatWebSocketManager @Inject constructor(
    private val gson: Gson
) {

    private var webSocket: WebSocket? = null
    private val client = OkHttpClient()

    fun startConnection(userId: Int): Flow<MessageEntity> = callbackFlow {
        val url = "ws://54.88.186.16:8080/api/v1/message/subscribe?userId=$userId" // Corrected URL
        val request = Request.Builder().url(url).build()

        val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                println("WebSocket Conectado al servidor!")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                println("Nuevo mensaje recibido en tiempo real: $text")
                try {
                    val messageDto = gson.fromJson(text, Message::class.java)
                    trySend(messageDto.toDomain())
                } catch (e: Exception) {
                    println("Error al parsear el mensaje del WebSocket: ${e.message}")
                }
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                println("WebSocket Cerrado. Razón: $reason")
                close()
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                println("Error en WebSocket: ${t.message}")
                close(t)
            }
        }

        webSocket = client.newWebSocket(request, listener)

        awaitClose { 
            webSocket?.close(1000, "Flow cerrado") 
        }
    }

    fun closeConnection() {
        webSocket?.close(1000, "Cierre manual")
    }
}