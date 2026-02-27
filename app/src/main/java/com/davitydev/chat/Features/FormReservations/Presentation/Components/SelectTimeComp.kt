package com.davitydev.chat.Features.FormReservations.Presentation.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun SelectTimeComp(
    label: String,
    options: List<Time>,
    selected: Time?,
    onSelected: (Time) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selected?.let { formatTime(it) } ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { time ->
                DropdownMenuItem(
                    text = { Text(formatTime(time)) },
                    onClick = {
                        onSelected(time)
                        expanded = false
                    }
                )
            }
        }
    }
}

fun formatTime(time: Time): String {
    val formatter = SimpleDateFormat("h:mm a", Locale.getDefault())
    return formatter.format(time)
}
