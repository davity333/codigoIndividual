package com.davitydev.chat.Core.Navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.davitydev.chat.Features.Chat.Presentation.Screen.ContactsScreen
import com.davitydev.chat.Features.FormReservations.Presentation.Screen.PageReservationScreen
import com.davitydev.chat.Features.FormReservations.Presentation.Viewmodel.FormReservationViewmodel
import com.davitydev.chat.Features.Home.Presentation.Screen.HomeScreen
import com.davitydev.chat.Features.Home.Presentation.Viewmodel.HomeViewModel

class NavigationsApp : HomeNavGraph {
    override fun homeGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {

        navGraphBuilder.composable<Home> {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                onClickMessages = { navController.navigate(Chat) },
                onClickFormReservation = { navController.navigate(FormReservation) },
                onClickHome = {
                    navController.navigate(Home) {
                        popUpTo(Home) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }


        navGraphBuilder.composable<Chat> {  // ← nueva ruta
            ContactsScreen()
        }

        navGraphBuilder.composable<FormReservation>{
            val viewModel: FormReservationViewmodel = hiltViewModel()
            PageReservationScreen(
                onClickMessages = { navController.navigate(Login) }
            )
        }
    }
}