package com.davitydev.chat.Features.Chat.Presentation.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davitydev.chat.Features.Chat.Domain.Entities.ContactEntity
import com.davitydev.chat.Features.Chat.Presentation.ViewModel.ContactsViewModel
import com.example.compose.Orange
import com.example.compose.blueSky
import com.example.compose.onSurfaceVariantLight
import com.example.compose.outlineLight
import com.example.compose.outlineVariantLight
import com.example.compose.primaryContainerLight
import com.example.compose.primaryLight
import com.example.compose.secondaryLight
import com.example.compose.surfaceContainerLowLight
import com.example.compose.surfaceContainerLight
import com.example.compose.onPrimaryContainerLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    viewModel     : ContactsViewModel = hiltViewModel(),
    onContactClick: (Int) -> Unit     = {},
    onBackClick   : () -> Unit        = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = surfaceContainerLowLight,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Contactos",
                        color      = primaryLight,
                        fontWeight = FontWeight.Bold,
                        fontSize   = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = primaryLight
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceContainerLowLight
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            // BUSCADOR
            Row(
                modifier          = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value         = uiState.searchText,
                    onValueChange = { viewModel.onSearchTextChange(it) },
                    placeholder   = {
                        Text(
                            text     = "Buscar contacto...",
                            color    = outlineLight,
                            fontSize = 14.sp
                        )
                    },
                    singleLine = true,
                    modifier   = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(14.dp)),
                    colors     = TextFieldDefaults.colors(
                        focusedContainerColor   = surfaceContainerLight,
                        unfocusedContainerColor = surfaceContainerLight,
                        focusedTextColor        = onSurfaceVariantLight,
                        unfocusedTextColor      = onSurfaceVariantLight,
                        focusedIndicatorColor   = primaryLight,
                        unfocusedIndicatorColor = outlineVariantLight,
                        cursorColor             = primaryLight
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier         = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(primaryLight)
                        .clickable { viewModel.searchUser() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector        = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint               = surfaceContainerLowLight,
                        modifier           = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // LOADING
            if (uiState.isLoading) {
                Box(
                    modifier         = Modifier.fillMaxWidth().padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = blueSky, strokeWidth = 3.dp)
                }
            }

            // RESULTADOS DE BÚSQUEDA
            if (uiState.searchedUsers.isNotEmpty()) {
                Text(
                    text       = "RESULTADOS",
                    color      = outlineLight,
                    fontSize   = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier   = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(
                    modifier       = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 220.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(surfaceContainerLight),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    items(uiState.searchedUsers) { user ->
                        val isAlreadyContact = uiState.contacts.any { it.contactId == user.idUser }
                        Row(
                            modifier          = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier         = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(primaryContainerLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector        = Icons.Default.Person,
                                    contentDescription = null,
                                    tint               = primaryLight,
                                    modifier           = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text     = user.username ?: "(Sin nombre)",
                                color    = onSurfaceVariantLight,
                                fontSize = 14.sp,
                                modifier = Modifier.weight(1f)
                            )

                            if (!isAlreadyContact) {
                                Box(
                                    modifier         = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Orange.copy(alpha = 0.12f))
                                        .clickable { viewModel.addContact(user.idUser) }
                                        .padding(horizontal = 10.dp, vertical = 6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector        = Icons.Default.Add,
                                            contentDescription = "Agregar",
                                            tint               = Orange,
                                            modifier           = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text       = "Añadir",
                                            color      = Orange,
                                            fontSize   = 12.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }

                        if (uiState.searchedUsers.last() != user) {
                            HorizontalDivider(
                                color    = outlineVariantLight,
                                thickness = 0.5.dp,
                                modifier  = Modifier.padding(horizontal = 14.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            // MIS CONTACTOS
            Text(
                text       = "MIS CONTACTOS",
                color      = outlineLight,
                fontSize   = 11.sp,
                fontWeight = FontWeight.SemiBold,
                modifier   = Modifier.padding(bottom = 10.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier            = Modifier.fillMaxWidth()
            ) {
                items(uiState.contacts) { contact ->
                    ContactItem(
                        contact        = contact,
                        onContactClick = { onContactClick(contact.contactId) }
                    )
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
            .clip(RoundedCornerShape(14.dp))
            .background(surfaceContainerLight)
            .clickable { onContactClick() }
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier         = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(primaryContainerLight),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text       = contact.firstName?.take(1)?: "",
                color      = onPrimaryContainerLight,
                fontSize   = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text       = "${contact.firstName} ${contact.lastName}",
                color      = onSurfaceVariantLight,
                fontSize   = 15.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text     = "Toca para chatear",
                color    = outlineLight,
                fontSize = 12.sp
            )
        }
    }
}