package com.davitydev.chat.Core.Navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface FeatureNavGraph {
    fun loginGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    )
}

interface HomeNavGraph {
    fun homeGraph(navGraphBuilder: NavGraphBuilder, navController: NavController)
}