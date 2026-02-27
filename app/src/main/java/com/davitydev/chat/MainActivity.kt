package com.davitydev.chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.davitydev.chat.Core.navigation.FeatureNavGraph
import com.davitydev.chat.Core.navigation.HomeNavGraph
import com.davitydev.chat.Core.di.NavigationApp
import com.example.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var loginNavGraph: FeatureNavGraph

    @Inject
    lateinit var homeNavGraph: HomeNavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                NavigationApp(
                    loginNavGraph = loginNavGraph,
                    homeNavGraph = homeNavGraph
                )
            }
        }
    }
}
