package com.davitydev.chat.Core.di

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.davitydev.chat.Core.Navigation.Login
import com.davitydev.chat.Features.Home.Presentation.Navigation.HomeNavGraph
import com.davitydev.chat.Features.Login.Presentation.Navigation.LoginNavGraph

@Composable
fun NavigationApp(){
    val navController = rememberNavController()
    val loginNav = LoginNavGraph()
    val homeNav = HomeNavGraph()

    NavHost(
        navController = navController,
        startDestination = Login
    ){
        loginNav.loginGraph(this, navController)
        homeNav.homeGraph(this, navController)
    }
}