package com.davitydev.chat.Features.Login.Presentation.Navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.davitydev.chat.Core.Navigation.FeatureNavGraph
import com.davitydev.chat.Core.Navigation.Login
import com.davitydev.chat.Core.Navigation.Register
import com.davitydev.chat.Features.Login.Presentation.Screen.LoginScreen
import com.davitydev.chat.Features.Login.Presentation.Viewmodel.LoginViewModel
import com.davitydev.chat.Features.Register.Presentation.Screen.RegisterScreen
import androidx.hilt.navigation.compose.hiltViewModel

class LoginNavGraph : FeatureNavGraph {
    override fun loginGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {

        navGraphBuilder.composable<Login> {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                viewModel = viewModel,
                onClickRegister = { navController.navigate(Register) }
            )
        }

        navGraphBuilder.composable<Register> {
            val viewModel: LoginViewModel = hiltViewModel()
            RegisterScreen(
                viewModel = viewModel,
                onClickLogin = { navController.navigate(Login) }
            )
        }

    }
}