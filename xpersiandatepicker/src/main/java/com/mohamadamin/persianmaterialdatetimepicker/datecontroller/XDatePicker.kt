package com.mohamadamin.persianmaterialdatetimepicker.datecontroller

import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import java.util.*

open class XDatePicker(
    private val appCompatActivity: AppCompatActivity
) {

    val calendar: PersianCalendar = PersianCalendar()

    //persian
    protected var year: Int = 0
    protected var month: Int = 0
    protected var day: Int = 0

    init {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        this.year = calendar.persianYear
        this.month = calendar.persianMonth
        this.day = calendar.persianDay
    }

    fun showDatePicker(
        listener: ((datePickerDialog: DatePickerDialog, year: Int, month: Int, day: Int) -> Unit)
    ): XDatePicker {
        buildAndShowDatePicker(listener)
        return this
    }

    fun showDatePicker(
        @NonNull listener: DatePickerListener
    ): XDatePicker {
        buildAndShowDatePicker { datePickerDialog: DatePickerDialog, year: Int, month: Int, day: Int ->
            listener.onDatePicked(datePickerDialog, year, month, day)
        }
        return this
    }

    fun showDatePicker(
        day: Int,
        month: Int,
        year: Int,
        @NonNull listener: ((datePickerDialog: DatePickerDialog, year: Int, month: Int, day: Int) -> Unit)
    ): XDatePicker {
        this.year = year
        this.month = month
        this.day = day

        buildAndShowDatePicker(listener)
        return this
    }

    fun showDatePicker(
        day: Int,
        month: Int,
        year: Int,
        @NonNull listener: DatePickerListener
    ): XDatePicker {
        this.year = year
        this.month = month
        this.day = day

        buildAndShowDatePicker { datePickerDialog: DatePickerDialog, year: Int, month: Int, day: Int ->
            listener.onDatePicked(datePickerDialog, year, month, day)
        }
        return this
    }

    private fun buildAndShowDatePicker(listener: ((datePickerDialog: DatePickerDialog, day: Int, month: Int, year: Int) -> Unit)) {
        val datePickerDialog =
            DatePickerDialog.newInstance({ datePickerDialog: DatePickerDialog, i: Int, i1: Int, i2: Int ->
                listener(datePickerDialog, day, month, year)
            }, this.year, this.month, this.day)

        datePickerDialog.show(appCompatActivity.fragmentManager, "TAG")
    }

    public fun getActivity(): AppCompatActivity {
        return appCompatActivity
    }

}