package com.mohamadamin.persianmaterialdatetimepicker.datecontroller

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog

interface DatePickerListener {
    fun onDatePicked(datePickerDialog: DatePickerDialog, year: Int, month: Int, day: Int)
}