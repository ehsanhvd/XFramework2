package com.hvd.xview.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.hvd.xview.R
import com.hvd.xview.utils.XCustomViews

open class CustomEditText : AppCompatEditText {

    constructor(context: Context) : super(context){
        init(context, null, 0)
    }

    constructor(context: Context, attrs : AttributeSet) : super(context,attrs){
        init(context, attrs, 0)
    }

    constructor(context: Context,  attrs: AttributeSet , defStyleAttr : Int) : super(context, attrs, defStyleAttr){
        init(context, attrs, defStyleAttr)
    }

    fun init(context: Context,  attrs: AttributeSet? , defStyleAttr : Int) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText, defStyleAttr, 0)
        val fontI = ta.getInt(R.styleable.CustomEditText_fontIndex, 0)

        typeface = XCustomViews.getTypefaces()[fontI]
        ta.recycle()
    }

}