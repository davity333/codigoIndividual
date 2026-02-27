package com.davitydev.chat.Features.Class.Presentation.Screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoChip(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = label, fontSize = 13.sp)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = value,
            fontSize = 12.sp,
            color = Color(0xFF475569),
            fontWeight = FontWeight.Medium
        )
    }
}