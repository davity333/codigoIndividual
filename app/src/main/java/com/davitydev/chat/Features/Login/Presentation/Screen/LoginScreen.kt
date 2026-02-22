package com.davitydev.chat.Features.Login.Presentation.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.davitydev.chat.Features.Login.Presentation.Viewmodel.LoginViewModel
import com.davitydev.chat.R
import com.example.compose.primaryContainerDarkMediumContrast
import com.example.compose.surfaceBrightDarkMediumContrast
import com.example.ui.theme.displayFontFamily

@Composable
fun LoginScreen(viewModel: LoginViewModel,
                onClickRegister: () -> Unit) {
    val text = remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        Circle(200, -50, -50, 0.4f)
        Circle(180, 50, -80, 0.3f)

        Column(modifier = Modifier.fillMaxSize(),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(R.drawable.icon),
                contentDescription = "menu",
                modifier = Modifier.size(70.dp))
            Spacer(modifier = Modifier.size(20.dp))
            Text(fontSize = 25.sp, text = "INICIAR SESIÓN",
                fontFamily = displayFontFamily, color = surfaceBrightDarkMediumContrast)
            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                value = text.value,
                onValueChange = { text.value = it },
                label = { Text("Usuario") }
            )
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                value = text.value,
                onValueChange = { text.value = it },
                label = { Text("Contraseña") }
            )

            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {},
                modifier = Modifier.fillMaxWidth(0.8f),

                        colors = ButtonDefaults.buttonColors(
                        containerColor = primaryContainerDarkMediumContrast
                        )) {
                Text("Entrar", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.size(10.dp))

            Row() {
                Text(fontSize = 18.sp, text = "No tienes cuenta?",
                    fontFamily = displayFontFamily,
                    color = surfaceBrightDarkMediumContrast)
                Spacer(modifier = Modifier.width(8.dp))
                Text(fontSize = 18.sp, text = "Registrate",
                    fontFamily = displayFontFamily,
                    color = surfaceBrightDarkMediumContrast,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable{
                        onClickRegister()
                    })
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun LoginView(){
    LoginScreen()
}
*/