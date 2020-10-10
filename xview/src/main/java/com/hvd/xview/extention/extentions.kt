package com.hvd.xview.extention

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

fun ViewGroup.addView(ctext: Context, view: AnkoComponent<ViewGroup>){
    this.addView(view.createView(AnkoContext.create(ctext, this)))
}

fun Fragment.addParam(name: String, value: String) {
    var arguments = arguments;
    if (arguments == null) {
        arguments = Bundle()
    }

    arguments.putString(name, value)
    this.arguments = arguments
}
