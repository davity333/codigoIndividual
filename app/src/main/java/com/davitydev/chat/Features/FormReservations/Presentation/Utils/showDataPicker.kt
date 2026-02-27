package com.davitydev.chat.Features.FormReservations.Presentation.Utils

import android.app.DatePickerDialog
import android.content.Context
import java.util.Calendar

fun showDatePicker(
    context: Context,
    onDateSelected: (day: Int, month: Int, year: Int) -> Unit
) {
    val calendar = Calendar.getInstance()

    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            onDateSelected(dayOfMonth, month, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}
