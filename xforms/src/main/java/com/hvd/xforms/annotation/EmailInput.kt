package com.tpa.formbuilder.annotation

import android.view.View
import android.view.inputmethod.EditorInfo

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class EmailInput(
    val displayName: String = "",
    val imeOpt: Int = EditorInfo.IME_ACTION_NEXT,
    val mandatory: Boolean = false,
    val id: Int = View.NO_ID
)