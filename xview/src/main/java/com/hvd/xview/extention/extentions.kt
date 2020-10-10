package com.hvd.xview.extention

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import java.io.Serializable


fun View.setText(id: Int, text: String) {
    findViewById<TextView>(id).setText(text)
}

fun Fragment.addParam(name: String, value: String) {
    var arguments = arguments;
    if (arguments == null) {
        arguments = Bundle()
    }

    arguments.putString(name, value)
    this.arguments = arguments
}

fun Fragment.addParam(name: String, value: Boolean) {
    var arguments = arguments;
    if (arguments == null) {
        arguments = Bundle()
    }

    arguments.putBoolean(name, value)
    this.arguments = arguments
}

fun Fragment.addParam(name: String, value: Int) {
    var arguments = arguments;
    if (arguments == null) {
        arguments = Bundle()
    }

    arguments.putInt(name, value)
    this.arguments = arguments
}

fun Fragment.addParam(name: String, value: Long) {
    var arguments = arguments;
    if (arguments == null) {
        arguments = Bundle()
    }

    arguments.putLong(name, value)
    this.arguments = arguments
}

fun Fragment.addParam(name: String, value: Serializable) {
    var arguments = arguments;
    if (arguments == null) {
        arguments = Bundle()
    }

    arguments.putSerializable(name, value)
    this.arguments = arguments
}

fun Fragment.addParam(name: String, value: Parcelable) {
    var arguments = arguments;
    if (arguments == null) {
        arguments = Bundle()
    }

    arguments.putParcelable(name, value)
    this.arguments = arguments
}
