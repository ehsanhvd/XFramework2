package com.hvd.xframework

import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.hvd.xforms.Form
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar

object FormGeneratorHelper {
    fun generateManual(activity: AppCompatActivity, parent: LinearLayout){
        val form = Form.with(activity, parent).editText("text 1").editText("text 2")
            .row()
            .editText("text 3").button("do something") {
//                activity.showSnackbar("done something")
            }
            .row()
            .checkbox("Checkbox", id = R.id.checkTest)
            .editText(hint = "dependant edit", id = R.id.editAnother).dependsOn(R.id.checkTest)
            .text("dependant label", id = R.id.textLabel).dependsOn(R.id.checkTest)
            .row()
            .spinner(R.array.testArray).space().space()
            .row()
            .datePicker(hint = "Jalali date picker", format = PersianCalendar.DATE_FORMAT_FULL).dateTimePicker(hint = "some date")
            .row()
            .radioGroup(orientation = LinearLayout.VERTICAL)
            .radioButton("some option", id = R.id.radioOption).radioButton("another option")
            .row()
            .editText("some dependant text", id = R.id.textDependant).dependsOn(R.id.radioOption)
            .checkbox("wrap check", R.id.checkWrapDep).wrapContent().dependsOn(R.id.radioOption)
            .row().row()
            .iranTelInput(hint = "iran tel")
            .emailInput(hint ="mandatory email", mandatory = true)
            .finish()

    }

    fun generateAuto(activity: AppCompatActivity, parent: LinearLayout){
        Form.with(activity, parent, Person("name")).finish()
    }
}