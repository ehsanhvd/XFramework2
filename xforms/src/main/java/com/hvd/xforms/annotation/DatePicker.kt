package com.tpa.formbuilder.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class DatePicker(
    val displayName: String = "",
    val id: Int = 0
) {
}