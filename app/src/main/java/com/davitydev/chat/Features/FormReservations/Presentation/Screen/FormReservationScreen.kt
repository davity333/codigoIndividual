package com.davitydev.chat.Features.FormReservations.Presentation.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davitydev.chat.Features.FormReservations.Presentation.Viewmodel.FormReservationViewmodel
import com.davitydev.chat.Features.Home.Presentation.Components.Header
import com.davitydev.chat.R
import com.example.compose.Orange
import com.example.compose.onSurfaceVariantLight
import com.example.ui.theme.bodyFontFamily
import com.example.ui.theme.displayFontFamily
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun FormReservationScreen (
    viewModel: FormReservationViewmodel = hiltViewModel(),
    onClickMessages: () -> Unit){

    val username by viewModel.username.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(0.dp),
                clip = false,
                ambientColor = Color.Black.copy(alpha = 0.25f),
                spotColor = Color.Black.copy(alpha = 0.25f)
            )) {
        Header(
            userName = username,
            onClickMessages = onClickMessages)
        }

        //CONTENIDO
        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.weight(3f)) {
                Text("Agenda tu sesión!",
                    fontFamily = displayFontFamily,
                    fontSize = 18.sp,
                    color = Orange
                )
                Text(
                    "Para agendar tu sesión, primero selecciona " +
                            "la fecha y luego verifica si hay " +
                            "espacio disponible en el horario que " +
                            "deseas (máximo 7 por hora)",
                    fontFamily = bodyFontFamily,
                    fontSize = 15.sp,
                    color = onSurfaceVariantLight,
                )
            }

            Image(
                painter = painterResource(R.drawable.calendario),
                contentDescription = "Logo",
                modifier = Modifier.size(50.dp)
                    .weight(1f)
            )
        }
    }
}


