package com.davitydev.chat.Core.di

import androidx.compose.runtime.Composable
import com.davitydev.chat.Core.navigation.FeatureNavGraph
import com.davitydev.chat.Core.navigation.HomeNavGraph
import com.davitydev.chat.Core.navigation.NavigationWrapper

@Composable
fun NavigationApp(
    loginNavGraph: FeatureNavGraph,
    homeNavGraph: HomeNavGraph
) {
    NavigationWrapper(
        loginNavGraphs = listOf(loginNavGraph),
        homeNavGraphs = listOf(homeNavGraph)
    )
}
