package com.davitydev.chat.Features.Home.Presentation.Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davitydev.chat.Features.Home.Presentation.Components.Header
import com.davitydev.chat.Features.Home.Presentation.Viewmodel.HomeViewModel
import com.davitydev.chat.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClickHome: () -> Unit,
    viewModel: HomeViewModel,
    onClickMessages: () -> Unit,
    onClickFormReservation : () -> Unit
) {
    val userName by viewModel.username.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {

        // HEADER
        Header(
            onClickHome            = { onClickHome() },
            userName               = userName,
            onClickMessages        = onClickMessages,
            onClickReservar        = { onClickFormReservation() },
            onClickVerReservaciones = { /* navega a ver reservaciones */ }
        )

        // CONTENIDO PRINCIPAL
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                ) {

                    Image(
                        painter = painterResource(R.drawable.fondo_azul),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Image(
                                painter = painterResource(R.drawable.primera_nota),
                                contentDescription = null,
                                modifier = Modifier.size(150.dp).offset(y = 40.dp).zIndex(10f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = painterResource(R.drawable.escuela),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp).padding(10.dp),
                    contentScale = ContentScale.FillWidth
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFFF2F2F2))
                        .padding(24.dp)
                ) {

                    Image(
                        painter = painterResource(R.drawable.mancha_codigo),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(180.dp),
                        contentScale = ContentScale.Fit
                    )

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top
                    ) {

                        Text(
                            text = "¡Bienvenido a Código Individual!",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD46A2E)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Aquí comienza tu camino para convertirte en programador. " +
                                    "No importa si estás empezando desde cero o si ya tienes curiosidad " +
                                    "por crear tus primeras apps: este es tu espacio para aprender a tu ritmo, " +
                                    "sin presión y con acompañamiento real.",
                            fontSize = 16.sp,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }
}

