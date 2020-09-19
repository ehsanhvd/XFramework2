package com.mohamadamin.persianmaterialdatetimepicker.datecontroller

import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.XDatePicker
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import org.jetbrains.annotations.NotNull

class XDateTimePicker(
    appCompatActivity: AppCompatActivity
) : XDatePicker(appCompatActivity) {

    protected var hour: Int = 0
    protected var min: Int = 0
    var timeListener: ((TimePickerDialog, Int, Int, Int, Int, Int) -> Unit)? = null

    fun showDateTimePicker(
        listener: ((datePickerDialog: TimePickerDialog, day: Int, month: Int, year: Int, hour: Int, min: Int) -> Unit)? = null
    ): XDatePicker {
        timeListener = listener
        buildAndShowDatePicker()
        return this
    }

    fun showDateTimePicker(
        @NotNull listener: DateTimePickerListener
    ): XDatePicker {
        timeListener = { timePickerDialog: TimePickerDialog, year: Int, month: Int, day: Int, min: Int, hour: Int ->
            listener.onDateTimePicked(this, year, month, day, hour, min)
        }
        buildAndShowDatePicker()
        return this
    }

    fun showDateTimePicker(
        day: Int,
        month: Int,
        year: Int,
        hour: Int,
        min: Int,
        listener: ((datePickerDialog: TimePickerDialog, day: Int, month: Int, year: Int, hour: Int, min: Int) -> Unit)? = null
    ): XDatePicker {
        timeListener = listener

        this.year = year
        this.month = month
        this.day = day
        this.hour = hour
        this.min = min

        buildAndShowDatePicker()
        return this
    }

    private fun buildAndShowDatePicker() {
        val datePickerDialog =
            DatePickerDialog.newInstance({ datePickerDialog: DatePickerDialog, i: Int, i1: Int, i2: Int ->
                showTimePicker()
            }, year, month, day)


        datePickerDialog.show(getActivity().fragmentManager, "TAG")
    }

    var timePicker: TimePickerDialog? = null

    private fun showTimePicker() {
        timePicker = TimePickerDialog.newInstance(
            { radialPickerLayout: RadialPickerLayout, hour: Int, min: Int ->
                this@XDateTimePicker.hour = hour
                this@XDateTimePicker.min = min
                if (timeListener != null) {
                    timeListener!!(timePicker!!, day, month, year, hour, min)
                }
            },
            hour,
            min,
            true
        )

        timePicker!!.show(getActivity().fragmentManager, "Datepickerdialog")
    }

}