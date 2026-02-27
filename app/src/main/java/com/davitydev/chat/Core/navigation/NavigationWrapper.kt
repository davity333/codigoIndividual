package com.davitydev.chat.Core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationWrapper(
    loginNavGraphs: List<FeatureNavGraph>,
    homeNavGraphs: List<HomeNavGraph>
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Login,
    ){
        loginNavGraphs.forEach { graph ->
            graph.loginGraph(this, navController)
        }
        homeNavGraphs.forEach { graph ->
            graph.homeGraph(this, navController)
        }
    }
}