package com.davitydev.chat.Features.FormReservations.Presentation.Viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FormReservationViewmodel @Inject constructor(): ViewModel() {
    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()


}