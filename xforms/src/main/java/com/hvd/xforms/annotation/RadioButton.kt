package com.tpa.formbuilder.annotation

import android.view.View
import androidx.annotation.ArrayRes

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class RadioButton(
    val displayName: String,
    @ArrayRes val itemsArray: Int,
    val id: Int = View.NO_ID
)