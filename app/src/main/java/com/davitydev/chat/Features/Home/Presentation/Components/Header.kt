package com.davitydev.chat.Features.Home.Presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davitydev.chat.R
import com.example.compose.blueSky
import com.example.compose.secondaryLight
import com.example.ui.theme.bodyFontFamily
import com.example.ui.theme.displayFontFamily

@Composable
fun Header(
    onClickHome         : () -> Unit = {},
    onClickMessages      : () -> Unit,
    onClickReservar      : () -> Unit = {},
    onClickVerReservaciones: () -> Unit = {},
    userName             : String
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 28.dp)
    ) {

        // LOGO + NOMBRE
        Row(
            modifier          = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter           = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier          = Modifier.size(70.dp).clickable { onClickHome() }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text("CODAL",       fontFamily = displayFontFamily, color = blueSky,         fontSize = 17.sp)
                Text(userName.ifEmpty { "Usuario" }, fontFamily = bodyFontFamily, color = secondaryLight, fontSize = 15.sp)
            }
        }

        // MENÚ + AVATAR
        Row(
            modifier          = Modifier.offset(y = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box {
                Icon(
                    imageVector        = Icons.Default.Menu,
                    contentDescription = "Menú",
                    modifier           = Modifier
                        .size(28.dp)
                        .clickable { menuExpanded = true },
                    tint               = secondaryLight
                )

                DropdownMenu(
                    expanded          = menuExpanded,
                    onDismissRequest  = { menuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text    = { Text("Mensajes", fontFamily = bodyFontFamily) },
                        onClick = {
                            menuExpanded = false
                            onClickMessages()
                        }
                    )
                    DropdownMenuItem(
                        text    = { Text("Reservar", fontFamily = bodyFontFamily) },
                        onClick = {
                            menuExpanded = false
                            onClickReservar()
                        }
                    )
                    DropdownMenuItem(
                        text    = { Text("Ver reservaciones", fontFamily = bodyFontFamily) },
                        onClick = {
                            menuExpanded = false
                            onClickVerReservaciones()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter            = painterResource(R.drawable.user_header),
                contentDescription = "Usuario",
                modifier           = Modifier.size(50.dp)
            )
        }
    }
}