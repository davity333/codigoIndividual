package com.davitydev.chat.Features.Class.Presentation.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davitydev.chat.Features.Class.Domain.Entities.Class

@Composable
fun ClassCard(
    clase: Class,
    onReservar: (classId: Int) -> Unit = {}
) {
    val statusColor = when (clase.status) {
        "Activa"    -> Color(0xFF059669)
        "Cancelada" -> Color(0xFFDC2626)
        else        -> Color(0xFFD97706)
    }
    val statusBg = when (clase.status) {
        "Activa"    -> Color(0xFFD1FAE5)
        "Cancelada" -> Color(0xFFFEE2E2)
        else        -> Color(0xFFFEF3C7)
    }

    Card(
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier            = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment   = Alignment.CenterVertically
            ) {
                Text(
                    text       = clase.title,
                    fontWeight = FontWeight.Bold,
                    fontSize   = 15.sp,
                    color      = Color(0xFF1E293B),
                    modifier   = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .background(statusBg, RoundedCornerShape(20.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text       = clase.status,
                        color      = statusColor,
                        fontSize   = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            if (clase.description.isNotEmpty()) {
                Text(text = clase.description, fontSize = 13.sp, color = Color(0xFF64748B), maxLines = 2)
                Spacer(modifier = Modifier.height(8.dp))
            }

            HorizontalDivider(color = Color(0xFFF1F5F9), thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoChip(label = "📅", value = clase.classDate)
                    InfoChip(label = "🕐", value = "${clase.startTime.take(5)} – ${clase.endTime.take(5)}")
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoChip(label = "👥", value = "${clase.capacity} alumnos")
                    InfoChip(label = "👨‍🏫", value = "${clase.firstName} ${clase.lastName}")
                }
            }

            // Solo muestra botón si la clase está Activa
            if (clase.status == "Activa") {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick  = { onReservar(clase.id) },
                    modifier = Modifier.fillMaxWidth().height(40.dp),
                    shape    = RoundedCornerShape(10.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C3AED))
                ) {
                    Text(text = "Reservar", fontSize = 13.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}