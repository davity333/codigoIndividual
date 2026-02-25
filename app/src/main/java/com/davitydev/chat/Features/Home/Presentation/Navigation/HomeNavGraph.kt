package com.davitydev.chat.Features.Home.Presentation.Navigation

import ChatScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.davitydev.chat.Core.Navigation.FeatureNavGraph
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.davitydev.chat.Core.Navigation.Chat
import com.davitydev.chat.Core.Navigation.Home
import com.davitydev.chat.Core.Navigation.HomeNavGraph
import com.davitydev.chat.Features.Home.Presentation.Screen.HomeScreen
import com.davitydev.chat.Features.Home.Presentation.Viewmodel.HomeViewModel
import com.davitydev.chat.Features.Login.Presentation.Screen.LoginScreen
import com.davitydev.chat.Features.Login.Presentation.Viewmodel.LoginViewModel

class HomeNavGraph : HomeNavGraph {
    override fun homeGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {

        navGraphBuilder.composable<Home> {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                onClickMessages = { navController.navigate(Chat) }  // ← navega
            )
        }

        navGraphBuilder.composable<Chat> {  // ← nueva ruta
            ChatScreen()
        }
    }
}