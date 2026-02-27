package com.davitydev.chat.Features.FormReservations.Presentation.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davitydev.chat.Features.FormReservations.Presentation.Components.FormReservationComp
import com.davitydev.chat.Features.FormReservations.Presentation.Viewmodel.PageReservationViewmodel
import com.davitydev.chat.Features.Home.Presentation.Components.Header
import com.davitydev.chat.R
import com.example.compose.Orange
import com.example.compose.onSurfaceVariantLight
import com.example.ui.theme.bodyFontFamily
import com.example.ui.theme.displayFontFamily

@Composable
fun PageReservationScreen(
    viewModel: PageReservationViewmodel = hiltViewModel(),
    onClickMessages: () -> Unit
) {

    val username by viewModel.username.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        // HEADER
        Column(
            modifier = Modifier.shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(0.dp),
                clip = false,
                ambientColor = Color.Black.copy(alpha = 0.25f),
                spotColor = Color.Black.copy(alpha = 0.25f)
            )
        ) {
            Header(
                userName = username,
                onClickMessages = onClickMessages
            )
        }

        // SCROLL
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            //CONTENIDO
            Row(
                modifier = Modifier.padding(20.dp)
            ) {

                // IZQUIERDA: TEXTO
                Column(
                    modifier = Modifier.weight(3f)
                ) {
                    Text(
                        "Agenda tu sesión!",
                        fontFamily = displayFontFamily,
                        fontSize = 22.sp,
                        color = Orange
                    )

                    Text(
                        "Para agendar tu sesión, primero selecciona la fecha y luego verifica si hay espacio disponible en el horario que deseas (máximo 7 por hora)",
                        fontFamily = bodyFontFamily,
                        fontSize = 15.sp,
                        color = onSurfaceVariantLight
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        "Una vez que elijas el horario, selecciona la materia de la clase de tu preferencia.",
                        fontFamily = bodyFontFamily,
                        fontSize = 15.sp,
                        color = onSurfaceVariantLight
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        "Cada materia cuenta con su propio salón asignado para las sesiones.",
                        fontFamily = bodyFontFamily,
                        fontSize = 15.sp,
                        color = onSurfaceVariantLight
                    )
                }

                // DERECHA: IMÁGENES
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 12.dp)
                ) {

                    Image(
                        painter = painterResource(R.drawable.calendario),
                        contentDescription = "Calendario",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            // LÍNEA
            Image(
                painter = painterResource(R.drawable.line),
                contentDescription = "LINEA",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )

            // CAJA DE FORMULARIO + NOTA
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                // NOTA (IMAGEN)
                Image(
                    painter = painterResource(R.drawable.segunda_nota),
                    contentDescription = "Nota",
                    modifier = Modifier
                        .zIndex(10f)
                        .align(Alignment.TopStart)
                        .offset(y = -130.dp, x = 210.dp)
                        .width(200.dp)
                        .height(220.dp),
                    contentScale = ContentScale.FillBounds
                )

                // FORMULARIO
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        "NUEVA SESIÓN",
                        modifier = Modifier.padding(start = 19.dp),
                        fontFamily = displayFontFamily,
                        fontSize = 22.sp,
                        color = Orange
                    )

                    FormReservationComp()
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


