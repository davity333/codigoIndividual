package com.davitydev.chat.Features.Login.Presentation.Navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.davitydev.chat.Core.navigation.FeatureNavGraph
import com.davitydev.chat.Core.navigation.Login
import com.davitydev.chat.Core.navigation.Register
import com.davitydev.chat.Features.Login.Presentation.Screen.LoginScreen
import com.davitydev.chat.Features.Login.Presentation.Viewmodel.LoginViewModel
import com.davitydev.chat.Features.Register.Presentation.Screen.RegisterScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davitydev.chat.Core.navigation.FormReservation
import com.davitydev.chat.Core.navigation.Home
import com.davitydev.chat.Features.FormReservations.Presentation.Screen.FormReservationScreen
import com.davitydev.chat.Features.FormReservations.Presentation.Viewmodel.FormReservationViewmodel

class LoginNavGraph : FeatureNavGraph {
    override fun loginGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {

        navGraphBuilder.composable<Login> {
            val viewModel: LoginViewModel = hiltViewModel()
            val navigateToHome by viewModel.navigateToHome.collectAsStateWithLifecycle()

            if (navigateToHome) {
                LaunchedEffect(Unit) {
                    navController.navigate(Home) {
                        popUpTo(Login) { inclusive = true }  // ← borra Login del back stack
                    }
                }
            }

            LoginScreen(
                viewModel = viewModel,
                onClickRegister = { navController.navigate(Register) },
                onClickFormReservation = { navController.navigate(FormReservation) }
            )
        }

        navGraphBuilder.composable<Register> {
            val viewModel: LoginViewModel = hiltViewModel()
            RegisterScreen(
                viewModel = viewModel,
                onClickLogin = { navController.navigate(Login) }
            )
        }

        navGraphBuilder.composable<FormReservation>{
            val viewModel: FormReservationViewmodel = hiltViewModel()
            FormReservationScreen(
                onClickMessages = { navController.navigate(Login) }
            )
        }


    }
}