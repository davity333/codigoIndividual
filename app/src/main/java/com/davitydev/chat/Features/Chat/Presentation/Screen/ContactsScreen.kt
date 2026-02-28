package com.davitydev.chat.Features.Chat.Presentation.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davitydev.chat.Features.Chat.Domain.Entities.ContactEntity
import com.davitydev.chat.Features.Chat.Presentation.ViewModel.ContactsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel = hiltViewModel(),
    onContactClick: (Int) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contactos") },
                actions = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Reply,
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { onBackClick() }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = uiState.searchText,
                    onValueChange = { viewModel.onSearchTextChange(it) },
                    placeholder = { Text("Buscar contacto para agregar") },
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { viewModel.searchUser() }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (uiState.isLoading) {
                CircularProgressIndicator()
            }

            LazyColumn(modifier = Modifier.height(200.dp)) { // Limit height for display
                items(uiState.searchedUsers) { user ->
                    val isAlreadyContact = uiState.contacts.any { it.contactId == user.idUser }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "User Icon",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = user.username ?: "(Usuario sin nombre)",
                            modifier = Modifier.weight(1f)
                        )
                        if (!isAlreadyContact) {
                            Button(onClick = { viewModel.addContact(user.idUser) }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Contact")
                                Spacer(modifier = Modifier.size(4.dp))
                                Text("Añadir")
                            }
                        }
                    }
                    HorizontalDivider()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Mis Contactos", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(uiState.contacts) { contact ->
                    ContactItem(contact = contact, onContactClick = {
                        onContactClick(contact.contactId)
                    })
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun ContactItem(contact: ContactEntity, onContactClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onContactClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Contact Icon",
            modifier = Modifier.size(40.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "#${contact.firstName} ${contact.lastName}", style = MaterialTheme.typography.bodyLarge)
    }
}
