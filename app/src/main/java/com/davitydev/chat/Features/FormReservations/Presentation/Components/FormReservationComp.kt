package com.davitydev.chat.Features.FormReservations.Presentation.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davitydev.chat.Features.Class.Presentation.Screen.CardClassScreen
import com.davitydev.chat.Features.FormReservations.Presentation.Utils.showDatePicker
import com.davitydev.chat.Features.FormReservations.Presentation.Viewmodel.FormReservationViewmodel
import com.example.ui.theme.bodyFontFamily

@Composable
fun FormReservationComp(
    viewModel: FormReservationViewmodel = hiltViewModel()
) {
    val context      = LocalContext.current
    val date         by viewModel.date.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    Spacer(modifier = Modifier.height(1.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {

        Text("Fecha seleccionada: ${date.ifEmpty { "—" }}")

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                showDatePicker(context) { d, m, y -> viewModel.validateAndSetDate(d, m, y) }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Text(
                text = "Seleccionar fecha",
                fontFamily = bodyFontFamily,
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Clases disponibles",
            fontFamily = bodyFontFamily,
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ← Pasa la fecha para filtrar automáticamente
        CardClassScreen(filterDate = date)

        Spacer(modifier = Modifier.height(20.dp))

    }
}