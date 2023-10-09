package com.example.goaltracker1

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

object Functions  {

        fun showDatePickerDialog(context: Context, editText: TextInputEditText, isFutureDateEnabled: Boolean,isPastDateEnabled: Boolean) {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    val formattedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                    editText.setText(formattedDate)
                },
                year,
                month,
                day
            )
            // Configurar el límite de fecha según el contexto
            if (!isFutureDateEnabled) {
                datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
            }

            if (!isPastDateEnabled) {
                 datePickerDialog.datePicker.minDate = System.currentTimeMillis()
             }
            datePickerDialog.show()
        }
    }


