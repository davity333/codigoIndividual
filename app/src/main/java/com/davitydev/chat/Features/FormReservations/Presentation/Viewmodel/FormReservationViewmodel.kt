package com.davitydev.chat.Features.FormReservations.Presentation.Viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class FormReservationViewmodel @Inject constructor() : ViewModel() {

    private val _date = MutableStateFlow("")
    val date = _date.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun validateAndSetDate(day: Int, month: Int, year: Int) {
        val selected = Calendar.getInstance().apply {
            set(year, month, day)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        if (selected.before(today)) {
            _errorMessage.value = "No puedes seleccionar una fecha anterior"
        } else {
            _errorMessage.value = ""
            _date.value = "%04d-%02d-%02d".format(year, month + 1, day)
        }
    }
}