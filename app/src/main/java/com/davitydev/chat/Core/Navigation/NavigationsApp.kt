package com.davitydev.chat.Core.Navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.davitydev.chat.Features.Chat.Presentation.Screen.ContactsScreen
import com.davitydev.chat.Features.Home.Presentation.Screen.HomeScreen
import com.davitydev.chat.Features.Home.Presentation.Viewmodel.HomeViewModel

class NavigationsApp : HomeNavGraph {
    override fun homeGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {

        navGraphBuilder.composable<Home> {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                onClickMessages = { navController.navigate(Chat) }  // ← navega
            )
        }

        navGraphBuilder.composable<Chat> {  // ← nueva ruta
            ContactsScreen()
        }
    }
}