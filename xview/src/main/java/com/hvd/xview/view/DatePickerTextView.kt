package com.hvd.xview.view

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import com.mohamadamin.persianmaterialdatetimepicker.XPersianCalendar
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.XDatePicker
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar


open class DatePickerTextView : BaseDatePickerTextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context,attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr : Int) : super(context, attrs, defStyleAttr)


    private fun getActivity(): Activity? {
        var context = context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    init {
        setOnClickListener {
            XDatePicker(getActivity() as AppCompatActivity).showDatePicker { datePickerDialog: DatePickerDialog, day: Int, month: Int, year: Int ->
                val calendar = XPersianCalendar(year, month, day)
                setText(calendar.persianShortFormat)

                val persianCalendar = PersianCalendar()
                persianCalendar.setPersianDate(year, month, day)
                time = persianCalendar.timeInMillis

                setError(null)
            }
        }
    }
}