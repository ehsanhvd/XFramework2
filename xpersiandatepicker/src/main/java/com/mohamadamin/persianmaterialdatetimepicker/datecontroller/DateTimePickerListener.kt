package com.mohamadamin.persianmaterialdatetimepicker.datecontroller

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog

interface DateTimePickerListener {
    fun onDateTimePicked(xDateTimePicker: XDateTimePicker, year: Int, month: Int, day: Int, hour: Int, min: Int)
}