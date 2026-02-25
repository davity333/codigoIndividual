package com.davitydev.chat.Features.Home.Presentation.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davitydev.chat.Features.Home.Presentation.Components.Header
import com.davitydev.chat.Features.Home.Presentation.Viewmodel.HomeViewModel
import com.davitydev.chat.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel,
               onClickMessages: () -> Unit){
    val userName by viewModel.username.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Header(onClickMessages = onClickMessages,
            userName = userName)
    }

}
