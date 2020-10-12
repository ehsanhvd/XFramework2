package com.hvd.xforms

import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.hvd.xcustomview.view.BaseDatePickerTextView
import com.tpa.formbuilder.annotation.*
import java.lang.reflect.Field
import java.util.*
import kotlin.reflect.KClass


class FormAnnotationProcessor(val appCompatActivity: AppCompatActivity, val form: Form, val entity: Any) {

    private val formFields = hashMapOf<Field, FormField>()

    init {
        val fields = entity.javaClass.declaredFields
        val orderedFields = arrayListOf<Field>()

        fields.forEach {
            if (it.isAnnotationPresent(Order::class.java)) {
                orderedFields.add(it)
            }
        }

        orderedFields.sortWith(Comparator { lastField: Field, nextField: Field ->
            val last = lastField.getAnnotation(Order::class.java)
            val next = nextField.getAnnotation(Order::class.java)


            return@Comparator last.order.compareTo(next.order)
        })

        var i = 0
        orderedFields.forEach { m ->
            val order = m.getAnnotation(Order::class.java).order

            if (m.isAnnotationPresent(Input::class.java)) {
                val ta = m.getAnnotation(Input::class.java)
                val keepRow = m.isAnnotationPresent(KeepRow::class.java)

                if (0 < i && !keepRow) {
                    form.row()
                }

                val formField = FormField(
                    Input::class,
                    editText(m, ta)
                )
                formFields.put(m, formField)

            } else if (m.isAnnotationPresent(DatePicker::class.java)) {
                val ta = m.getAnnotation(DatePicker::class.java)
                val keepRow = m.isAnnotationPresent(KeepRow::class.java)

                if (0 < i && !keepRow) {
                    form.row()
                }

                val formField = FormField(
                    DatePicker::class,
                    datePicker(m, ta)
                )
                formFields.put(m, formField)

            } else if (m.isAnnotationPresent(DateTimePicker::class.java)) {
                val ta = m.getAnnotation(DateTimePicker::class.java)
                val keepRow = m.isAnnotationPresent(KeepRow::class.java)

                if (0 < i && !keepRow) {
                    form.row()
                }

                val formField = FormField(
                    DateTimePicker::class,
                    dateTimePicker(m, ta)
                )
                formFields.put(m, formField)
            } else if (m.isAnnotationPresent(IranTelInput::class.java)) {
                val ta = m.getAnnotation(IranTelInput::class.java)
                val keepRow = m.isAnnotationPresent(KeepRow::class.java)

                if (0 < i && !keepRow) {
                    form.row()
                }

                val formField = FormField(
                    IranTelInput::class,
                    iranTelInput(m, ta)
                )
                formFields.put(m, formField)

            } else if (m.isAnnotationPresent(EmailInput::class.java)) {
                val ta = m.getAnnotation(EmailInput::class.java)
                val keepRow = m.isAnnotationPresent(KeepRow::class.java)

                if (0 < i && !keepRow) {
                    form.row()
                }

                val formField = FormField(
                    EmailInput::class,
                    emailInput(m, ta)
                )
                formFields.put(m, formField)
            } else if (m.isAnnotationPresent(CheckBox::class.java)) {
                val ta = m.getAnnotation(CheckBox::class.java)
                val keepRow = m.isAnnotationPresent(KeepRow::class.java)

                if (0 < i && !keepRow) {
                    form.row()
                }

                val formField = FormField(
                    CheckBox::class,
                    checkbox(m, ta)
                )
                formFields.put(m, formField)
            } else if (m.isAnnotationPresent(Spinner::class.java)) {
                val ta = m.getAnnotation(Spinner::class.java)
                val keepRow = m.isAnnotationPresent(KeepRow::class.java)

                if (0 < i && !keepRow) {
                    form.row()
                }

                val formField = FormField(
                    Spinner::class,
                    spinner(m, ta)
                )
                formFields.put(m, formField)
            } else if (m.isAnnotationPresent(RadioButton::class.java)) {
                val ta = m.getAnnotation(RadioButton::class.java)
                val keepRow = m.isAnnotationPresent(KeepRow::class.java)

                if (0 < i && !keepRow) {
                    form.row()
                }

                val formField = FormField(
                    RadioButton::class,
                    radio(m, ta)
                )
                formFields.put(m, formField)
            }
            if (m.isAnnotationPresent(WrapContent::class.java)) {
                form.wrapContent()
            }
            i++
        }
    }

    private fun editText(m: Field, ta: Input) = form.editText(
        text = getValue(entity, m).toString(),
        hint = ta.displayName,
        inputType = ta.inputType,
        imeOpt = ta.imeOpt,
        maxLength = ta.maxLenght,
        validator = getInstance(ta.validator),
        id = ta.id
    ).getLastView()!!

    private fun iranTelInput(m: Field, ta: IranTelInput) = form.iranTelInput(
        ta.displayName,
        getValue(entity, m).toString(),
        ta.imeOpt,
        ta.mandatory
    ).getLastView()!!

    private fun emailInput(m: Field, ta: EmailInput) = form.emailInput(
        ta.displayName,
        getValue(entity, m).toString(),
        ta.imeOpt,
        ta.mandatory
    ).getLastView()!!

    private fun checkbox(m: Field, ta: CheckBox) = form.checkbox(
        ta.displayName,
        checked = getValue(entity, m) as Boolean,
        id = ta.id
    ).getLastView()!!

    private fun spinner(m: Field, ta: Spinner) = form.spinner(
        ta.itemsArray,
        getValue(entity, m) as Int,
        ta.id
    ).getLastView()!!

    private fun radio(m: Field, ta: RadioButton): View {

        val lastView: View
        if (ta.id != 0) {
            lastView = form.radioGroup(id = ta.id).getLastView()!!
        } else {
            lastView = form.radioGroup().getLastView()!!
        }
        val items = appCompatActivity.resources.getStringArray(ta.itemsArray)

        for (i in 0 until items.size) {
            form.radioButton(items[i], checked = i == (getValue(entity, m) as Int))
        }

        return lastView //we've done this because we need to get radio group as last view
    }

    private fun datePicker(m: Field, ta: DatePicker) =
        form.datePicker(ta.displayName, getValue(entity, m) as Long, id = ta.id).getLastView()!!

    private fun dateTimePicker(m: Field, ta: DateTimePicker) =
        form.dateTimePicker(ta.displayName, getValue(entity, m) as Long, id = ta.id).getLastView()!!

    private fun getValue(entity: Any, field: Field): Any? {
        field.setAccessible(true)
        return field.get(entity)
    }

    fun populateToEntity() {
        formFields.forEach { (key, value) ->
            if (value.type.equals(Input::class) || value.type.equals(
                    IranTelInput::class) || value.type.equals(
                    EmailInput::class
                )
            ) {
                key.setAccessible(true)
                key.set(entity, (value.view as EditText).text.toString())
            } else if (value.type.equals(DatePicker::class) || value.type.equals(
                    DateTimePicker::class)) {
                key.setAccessible(true)
                key.set(entity, (value.view as BaseDatePickerTextView).time)
            } else if (value.type.equals(CheckBox::class)) {
                key.setAccessible(true)
                key.set(entity, (value.view as CompoundButton).isChecked)
            } else if (value.type.equals(Spinner::class)) {
                key.setAccessible(true)
                key.set(entity, (value.view as android.widget.Spinner).selectedItemPosition)
            } else if (value.type.equals(RadioButton::class)) {

                val checkedId = (value.view as RadioGroup).checkedRadioButtonId
                val checkedView = value.view.findViewById<View>(checkedId)

                key.setAccessible(true)
                key.set(entity, value.view.indexOfChild(checkedView))
            }
        }
    }

    private class FormField(val type: KClass<out Annotation>, val view: View)

    /* We have no way to guarantee that an empty constructor exists, so must return T? instead of T */
    fun <T : Any> getInstance(clazz: KClass<T>): T? {
        clazz.constructors.forEach { con ->
            if (con.parameters.size == 0) {
                return con.call()
            }
        }
        return null
    }

}