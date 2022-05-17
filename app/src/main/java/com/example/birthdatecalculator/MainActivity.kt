package com.example.birthdatecalculator

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.time.Month
import java.time.Year
import java.util.*
import java.util.Calendar.*

class MainActivity : AppCompatActivity() {

    private var textView4 : TextView? = null
    private var textView6 : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        textView4 = findViewById(R.id.textView4)
        textView6 = findViewById(R.id.textView6)

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){

        val myCalendar = getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            {view , selectedYear, selectedMonth, selectedDayOfMonth ->

            Toast.makeText(this,"Year was $selectedYear , month was ${selectedMonth+1}, day of month was $selectedDayOfMonth", Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                textView4?.text = selectedDate

                val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {

                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        textView6?.text = differenceInMinutes.toString()
                    }

                }

            },
            year,
            month,
            day
        )
       dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
       dpd.show()
    }
}