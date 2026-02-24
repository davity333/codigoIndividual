package com.davitydev.chat.Features.Login.Presentation.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davitydev.chat.Features.Login.Presentation.Viewmodel.LoginViewModel
import com.davitydev.chat.R
import com.example.compose.primaryContainerDarkMediumContrast
import com.example.compose.surfaceBrightDarkMediumContrast
import com.example.ui.theme.displayFontFamily

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onClickRegister: () -> Unit
) {
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val loginSuccess by viewModel.loginSuccess.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Circle(200, -50, -50, 0.4f)
        Circle(180, 50, -80, 0.3f)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = "logo",
                modifier = Modifier.size(70.dp)
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                fontSize = 25.sp,
                text = "INICIAR SESIÓN",
                fontFamily = displayFontFamily,
                color = surfaceBrightDarkMediumContrast
            )
            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                value = email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("Correo electrónico") }
            )
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                value = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = { viewModel.login() },
                modifier = Modifier.fillMaxWidth(0.8f),
                enabled = !loading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryContainerDarkMediumContrast
                )
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = Color.White
                    )
                } else {
                    Text("Entrar", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.size(10.dp))

            if (error.isNotEmpty()) {
                Text(
                    text = error,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (loginSuccess) {
                Text(
                    text = "¡Login exitoso!",
                    color = Color.Green,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.size(10.dp))
            Row {
                Text(
                    fontSize = 18.sp,
                    text = "¿No tienes cuenta?",
                    fontFamily = displayFontFamily,
                    color = surfaceBrightDarkMediumContrast
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    fontSize = 18.sp,
                    text = "Regístrate",
                    fontFamily = displayFontFamily,
                    color = surfaceBrightDarkMediumContrast,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onClickRegister() }
                )
            }
        }
    }
}