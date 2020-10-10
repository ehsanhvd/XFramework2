package com.hvd.xutils.extentions

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hvd.xutils.utils.ValidationUtils
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

fun Boolean.ifT(op: () -> Unit) {
    if (this) {
        op()
    }
}

fun Boolean.ifNT(op: () -> Unit) {
    if (this) {
        op()
    }
}

fun String.ifNotEmpty(op: () -> Any?) {
    if (!this.isEmpty()) {
        op()
    }
}

//fun Activity.getActivityLabel(): String? {
//    try {
//        val stringRes = this.packageManager.getActivityInfo(this.componentName, 0).labelRes
//        if (stringRes != 0) {
//            return this.resources.getString(stringRes)
//        }
//    } catch (var2: PackageManager.NameNotFoundException) {
//        var2.printStackTrace()
//    }
//
//    return null
//}


//not null
fun <T> NotNull(any: T?, op: ((it: T) -> Any)) {
    if (any != null) {
        op(any)
    }
}

//not null and not empty string
fun ValidString(text: String?, op: ((it: String) -> Any)) {
    if (ValidationUtils.isValidString(text)) {
        op(text!!)
    }
}

//fun Activity.showSnackbar(text: String) {
//    XUtil.showSnackbar(this.findViewById(android.R.id.content), text)
//}
//
//fun Fragment.showSnackbar(text: String) {
//    XUtil.showSnackbar(view!!, text)
//}
