package com.hvd.xcustomview.view

import android.content.Context
import android.util.AttributeSet

open class BaseDatePickerTextView : CustomEditText {

    var time = 0L

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context,attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr : Int) : super(context, attrs, defStyleAttr)

    init {
        setSingleLine()
        setFocusable(false)
        setClickable(false)
        setLongClickable(false)
    }
}