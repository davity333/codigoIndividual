package com.davitydev.chat.Features.Reservation.Presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davitydev.chat.Core.di.Token.TokenDataStore
import com.davitydev.chat.Features.Reservation.Domain.Usecases.CreateReservationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val createReservationUseCase: CreateReservationUseCase,
    private val tokenDataStore: TokenDataStore
) : ViewModel() {

    private val _isLoading  = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private val _success = MutableStateFlow(false)
    val success = _success.asStateFlow()

    fun createReservation(classId: Int, reservationDate: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            _success.value = false

            try {
                val token     = tokenDataStore.getToken().first() ?: ""
                val studentId = tokenDataStore.getIdUser().first() ?: 0

                // Toma solo la parte de fecha "2026-02-27" sin importar el formato que venga
                // El classDate viene como "2026-02-27T00:00:00-06:00", limpiamos:
                val cleanDate = reservationDate.take(10)  // "2026-02-27"
                val isoDate   = "${cleanDate}T00:00:00Z"  // "2026-02-27T00:00:00Z"

                val result = createReservationUseCase(
                    token           = token,
                    studentId       = studentId,
                    classId         = classId,
                    reservationDate = isoDate,
                    attendance      = true
                )

                result
                    .onSuccess { _success.value = true }
                    .onFailure { e ->
                        val msg = e.message ?: ""
                        _errorMessage.value = when {
                            msg.contains("duplicate", ignoreCase = true) -> "Ya tienes una reserva en este horario."
                            msg.contains("500",       ignoreCase = true) -> "Error del servidor, intenta más tarde."
                            else -> msg
                        }
                    }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error inesperado"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun resetState() {
        _success.value = false
        _errorMessage.value = ""
    }
}