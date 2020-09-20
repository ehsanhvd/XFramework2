package com.hvd.xcustomview.view

import android.content.Context
import android.util.AttributeSet
import android.widget.CheckBox
import com.hvd.xview.R

class CustomCheckbox : CheckBox {

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
        val ta = getContext().obtainStyledAttributes(attrs, R.styleable.CustomCheckbox, defStyleAttr, 0)
        val fontI = ta.getInt(R.styleable.CustomCheckbox_fontIndex, 0)

        setTypeface(com.hvd.xcore.XConfig.typefaces[fontI])
        ta.recycle()
    }
}