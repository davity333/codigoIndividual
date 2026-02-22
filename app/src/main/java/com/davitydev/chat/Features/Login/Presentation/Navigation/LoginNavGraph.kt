package com.davitydev.chat.Features.Login.Presentation.Navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.davitydev.chat.Core.Navigation.FeatureNavGraph
import com.davitydev.chat.Core.Navigation.Login
import com.davitydev.chat.Core.Navigation.Register
import com.davitydev.chat.Features.Login.Presentation.Screen.LoginScreen
import com.davitydev.chat.Features.Login.Presentation.Viewmodel.LoginViewModel
import com.davitydev.chat.Features.Register.Presentation.Screen.RegisterScreen
import com.davitydev.chat.Features.User.Di.LoginModule

class LoginNavGraph(private val LoginModule: LoginModule): FeatureNavGraph {
    override fun loginGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Login> {

            val factory = LoginModule.provideUsersViewModelFactory()
            val viewModel: LoginViewModel = viewModel(factory = factory)

            LoginScreen (
                viewModel = viewModel,
                onClickRegister = {navController.navigate(Register)}
            )
        }

        navGraphBuilder.composable<Register> {
            RegisterScreen()
        }
    }
}