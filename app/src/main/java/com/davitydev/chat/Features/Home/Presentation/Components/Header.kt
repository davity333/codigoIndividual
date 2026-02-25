package com.davitydev.chat.Features.Home.Presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davitydev.chat.R
import com.example.compose.blueSky
import com.example.compose.secondaryLight
import com.example.ui.theme.bodyFontFamily
import com.example.ui.theme.displayFontFamily

@Composable
fun Header(onClickMessages: () -> Unit,userName: String) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 28.dp)
    ) {

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(70.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    "CODAL",
                    fontFamily = displayFontFamily,
                    color = blueSky,
                    fontSize = 17.sp
                )
                Text(
                    userName.ifEmpty { "Usuario" },  // ← dinámico
                    fontFamily = bodyFontFamily,
                    color = secondaryLight,
                    fontSize = 15.sp
                )
            }
        }

        Row(
            modifier = Modifier.offset(y = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Message,
                contentDescription = "Mensajes",
                modifier = Modifier.size(28.dp)
                    .clickable{ onClickMessages()},
                tint = secondaryLight
            )

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(R.drawable.user_header),
                contentDescription = "Usuario",
                modifier = Modifier.size(50.dp)
            )
        }


    }
}
