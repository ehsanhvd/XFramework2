package com.hvd.xcustomview.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.hvd.xview.R
import com.hvd.xview.utils.XCustomViews

class CustomTextView : TextView {

    var attrs : AttributeSet? = null
    var defStyleAttr : Int = 0

    constructor(context: Context) : super(context){
    }

    constructor(context: Context, attrs : AttributeSet) : super(context,attrs){
        this.attrs = attrs
    }

    constructor(context: Context,  attrs: AttributeSet , defStyleAttr : Int) : super(context, attrs, defStyleAttr){
        this.attrs = attrs
        this.defStyleAttr = defStyleAttr
    }

    init {
        val ta = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextView, defStyleAttr, 0)
        val fontI = ta.getInt(R.styleable.CustomTextView_fontIndex, 0)

        if (XCustomViews.getTypefaces() != null && XCustomViews.getTypefaces().size > 0) {
            typeface = XCustomViews.getTypefaces()[fontI]
        }
        ta.recycle()
    }
}