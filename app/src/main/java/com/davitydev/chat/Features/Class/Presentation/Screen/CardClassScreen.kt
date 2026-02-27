package com.davitydev.chat.Features.Class.Presentation.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davitydev.chat.Features.Class.Presentation.Viewmodel.ClassViewModel
import com.davitydev.chat.Features.Reservation.Presentation.ReservationViewModel


@Composable
fun CardClassScreen(
    classViewModel      : ClassViewModel       = hiltViewModel(),
    reservationViewModel: ReservationViewModel = hiltViewModel(),
    filterDate          : String               = ""
) {
    val classes      by classViewModel.classes.collectAsStateWithLifecycle()
    val isLoading    by classViewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by classViewModel.errorMessage.collectAsStateWithLifecycle()

    val resLoading  by reservationViewModel.isLoading.collectAsStateWithLifecycle()
    val resError    by reservationViewModel.errorMessage.collectAsStateWithLifecycle()
    val resSuccess  by reservationViewModel.success.collectAsStateWithLifecycle()

    LaunchedEffect(filterDate) {
        if (filterDate.isNotEmpty()) {
            classViewModel.loadClassesByDate(filterDate)
        }
    }

    // Fecha que se pasa desde el formulario
    val displayed = if (filterDate.isNotEmpty())
        classes // Ya están filtradas por la llamada a loadClassesByDate en el LaunchedEffect
    else
        classes.filter { it.status == "Activa" }

    // Muestra mensaje de éxito o error de la reserva
    if (resSuccess) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .background(Color(0xFFD1FAE5), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(text = "✅ Reserva creada exitosamente", color = Color(0xFF059669), fontSize = 13.sp)
        }
    }

    if (resError.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .background(Color(0xFFFEE2E2), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(text = "⚠️ $resError", color = Color(0xFFDC2626), fontSize = 13.sp)
        }
    }

    when {
        isLoading -> {
            Box(
                modifier          = Modifier.fillMaxWidth().padding(24.dp),
                contentAlignment  = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color       = Color(0xFF7C3AED),
                    strokeWidth = 3.dp,
                    modifier    = Modifier.size(32.dp)
                )
            }
        }

        errorMessage.isNotEmpty() -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
                    .background(Color(0xFFFEE2E2), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Text(text = "⚠️ $errorMessage", color = Color(0xFFDC2626), fontSize = 13.sp)
            }
        }

        displayed.isEmpty() -> {
            Box(
                modifier         = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text     = if (filterDate.isNotEmpty()) "No hay clases activas para esta fecha." else "No hay clases disponibles.",
                    color    = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        else -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier            = Modifier.fillMaxWidth()
            ) {
                displayed.forEach { clase ->
                    ClassCard(
                        clase      = clase,
                        onReservar = { classId ->
                            reservationViewModel.resetState()
                            reservationViewModel.createReservation(
                                classId         = classId,
                                reservationDate = if (filterDate.isNotEmpty()) filterDate else clase.classDate
                            )
                        }
                    )
                }
            }
        }
    }
}