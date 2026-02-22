package com.davitydev.chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.davitydev.chat.Core.Navigation.NavigationWrapper
import com.davitydev.chat.Core.di.AppContainer
import com.davitydev.chat.Features.Login.Presentation.Navigation.LoginNavGraph
import com.davitydev.chat.Features.Login.Presentation.Screen.LoginScreen
import com.davitydev.chat.Features.User.Di.LoginModule
import com.example.compose.AppTheme

class MainActivity : ComponentActivity() {
    lateinit var appContainer: AppContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = AppContainer(this)
        val loginModule = LoginModule(appContainer)

        val navGraph = listOf(
            LoginNavGraph(loginModule)
        )

        enableEdgeToEdge()
        setContent {
            AppTheme {
                NavigationWrapper(navGraph)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("Android")
    }
}