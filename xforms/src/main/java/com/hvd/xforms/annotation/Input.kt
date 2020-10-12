package com.tpa.formbuilder.annotation

import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import com.hvd.xforms.Validator
import com.tpa.formbuilder.validator.DefaultValidator
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Input(
    val displayName: String = "",
    val inputType: Int = InputType.TYPE_CLASS_TEXT,
    val imeOpt: Int = EditorInfo.IME_ACTION_NEXT,
    val maxLenght: Int = 50,
    val validator: KClass<out Validator> = DefaultValidator::class,
    val id: Int = View.NO_ID
)