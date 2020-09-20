package com.hvd.xcustomview.view

import android.content.Context
import android.util.AttributeSet

open class BaseDatePickerTextView : CustomEditText {

    var format = PersianCalendar.DATE_FORMAT_SHORT

    var time = 0L

    constructor(context: Context) : super(context)

    constructor(context: Context, format: Int) : super(context){
        this.format = format
    }

    constructor(context: Context, attrs : AttributeSet) : super(context,attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr : Int) : super(context, attrs, defStyleAttr)

    init {
        singleLine = true
        setFocusable(false)
        setClickable(false)
        setLongClickable(false)
    }
}