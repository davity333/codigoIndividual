package com.davitydev.chat.Core.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.davitydev.chat.Features.Chat.Presentation.Screen.ChatScreen
import com.davitydev.chat.Features.Chat.Presentation.Screen.ContactsScreen
import com.davitydev.chat.Features.Chat.Presentation.ViewModel.ContactsViewModel
import com.davitydev.chat.Features.Home.Presentation.Screen.HomeScreen
import com.davitydev.chat.Features.Home.Presentation.Viewmodel.HomeViewModel

private const val CHAT_ROUTE = "chat"
private const val CONTACT_ID_ARG = "contactId"

class NavigationsApp : HomeNavGraph {
    override fun homeGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {

        navGraphBuilder.composable<Home> {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                onClickMessages = { navController.navigate(Chat) }
            )
        }

        navGraphBuilder.composable<Chat> {
            val viewModel: ContactsViewModel = hiltViewModel()
            ContactsScreen(
                viewModel = viewModel,
                onContactClick = {
                    navController.navigate("$CHAT_ROUTE/$it")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        navGraphBuilder.composable(
            route = "$CHAT_ROUTE/{$CONTACT_ID_ARG}",
            arguments = listOf(navArgument(CONTACT_ID_ARG) { type = NavType.IntType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getInt(CONTACT_ID_ARG) ?: 0
            ChatScreen(contactId = contactId)
        }
    }
}