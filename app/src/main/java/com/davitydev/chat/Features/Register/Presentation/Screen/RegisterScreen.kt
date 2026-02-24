package com.davitydev.chat.Features.Register.Presentation.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davitydev.chat.R
import com.example.compose.primaryContainerDarkMediumContrast
import com.example.compose.surfaceBrightDarkMediumContrast
import com.example.ui.theme.displayFontFamily
import com.davitydev.chat.Features.Login.Presentation.Screen.Circle
import com.davitydev.chat.Features.Login.Presentation.Viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(viewModel: LoginViewModel, onClickLogin: () -> Unit) {

    val nombre = remember { mutableStateOf("") }
    val usuario = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {

        // Círculos decorativos
        Circle(200, -50, -50, 0.4f)
        Circle(180, 50, -80, 0.3f)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = "icon",
                modifier = Modifier.size(70.dp)
            )

            Spacer(modifier = Modifier.size(20.dp))

            Text(
                text = "REGISTRO",
                fontSize = 25.sp,
                fontFamily = displayFontFamily,
                color = surfaceBrightDarkMediumContrast
            )

            Spacer(modifier = Modifier.size(20.dp))

            // Nombre
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                value = nombre.value,
                onValueChange = { nombre.value = it },
                label = { Text("Nombre") }
            )

            Spacer(modifier = Modifier.size(10.dp))

            // Usuario
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                value = usuario.value,
                onValueChange = { usuario.value = it },
                label = { Text("Usuario") }
            )

            Spacer(modifier = Modifier.size(10.dp))

            // Contraseña
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") }
            )

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryContainerDarkMediumContrast
                )
            ) {
                Text("Crear cuenta", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.size(10.dp))

            Row {
                Text(
                    text = "¿Ya tienes cuenta?",
                    fontSize = 18.sp,
                    fontFamily = displayFontFamily,
                    color = surfaceBrightDarkMediumContrast
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Inicia sesión",
                    fontSize = 18.sp,
                    fontFamily = displayFontFamily,
                    color = surfaceBrightDarkMediumContrast,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable{
                        onClickLogin()
                    }
                )
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun RegisterView() {
    RegisterScreen()
}
*/

