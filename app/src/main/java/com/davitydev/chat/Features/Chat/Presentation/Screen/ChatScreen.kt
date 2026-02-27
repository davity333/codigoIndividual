package com.davitydev.chat.Features.Chat.Presentation.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davitydev.chat.Features.Chat.Domain.Entities.MessageEntity
import com.davitydev.chat.Features.Chat.Presentation.ViewModel.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    contactId: Int,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = contactId) {
        viewModel.initializeChat(contactId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "User Icon",
                            modifier = Modifier.size(40.dp)
                        )
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(
                                text = "${uiState.chatPartner?.firstName ?: ""} ${uiState.chatPartner?.lastName ?: ""}".trim().ifEmpty { "Cargando..." },
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = uiState.chatPartner?.role ?: "",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Reply,
                        contentDescription = "Reply",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { onBackClick() }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = uiState.messageText,
                    onValueChange = { viewModel.onMessageChange(it) },
                    placeholder = { Text("Escribir mensaje") },
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                IconButton(onClick = { viewModel.sendMessage(contactId) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send Message"
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            reverseLayout = true // To show the newest messages at the bottom
        ) {
            items(uiState.messages.reversed()) { message ->
                Message(message = message, currentUserId = uiState.currentUserId)
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

@Composable
fun Message(message: MessageEntity, currentUserId: Int?) {
    val isFromMe = message.senderId == currentUserId
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isFromMe) Arrangement.End else Arrangement.Start
    ) {
        if (!isFromMe) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Sender Icon",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Bottom),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = if (isFromMe) Color(0xFFE7E7E7) else Color(0xFFE0F7FA)
        ) {
            Text(
                text = message.content,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        if (isFromMe) {
            Spacer(modifier = Modifier.size(8.dp))
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "My Icon",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Bottom),
                tint = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    val dummyMessage1 = MessageEntity(1, 2, 1, "Hola david, pásame la tarea del profe ali no la hice se me olvido hacerla, estaba trabajando y no me dio tiempo")
    val dummyMessage2 = MessageEntity(2, 1, 2, "ok no te preocupes bro")

    Column {
        Message(message = dummyMessage1, currentUserId = 1)
        Spacer(modifier = Modifier.height(8.dp))
        Message(message = dummyMessage2, currentUserId = 1)
    }
}
