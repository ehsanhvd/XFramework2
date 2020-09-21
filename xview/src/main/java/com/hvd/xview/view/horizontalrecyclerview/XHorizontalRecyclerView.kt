package com.hvd.xcustomview.view.horizontalrecyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.custom.ankoView

class XHorizontalRecyclerView : RecyclerView {
    var attrs : AttributeSet? = null
    var defStyleAttr : Int = 0

    constructor(context: Context) : super(context){
    }

    constructor(context: Context, attrs : AttributeSet) : super(context,attrs){
        this.attrs = attrs
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr : Int) : super(context, attrs, defStyleAttr){
        this.attrs = attrs
        this.defStyleAttr = defStyleAttr
    }

    init {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager = linearLayoutManager
    }
}

fun ViewManager.xhorizontalRecyclerView() = xhorizontalRecyclerView {}
inline fun ViewManager.xhorizontalRecyclerView(init: XHorizontalRecyclerView.() -> Unit) =
    ankoView({ XHorizontalRecyclerView(it) },0,  init)

