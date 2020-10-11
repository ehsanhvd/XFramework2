package com.hvd.xview.utils

import android.app.Activity
import android.app.Application
import android.text.Html
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar
import com.hvd.xview.R

class SnackBar {

    fun show(activity: Activity, message: String?, @ColorRes colorRes: Int): Snackbar? {
//        String colorCodeString = "#" + Integer.toHexString(ContextCompat.getColor(activity, R.color.darkerPersianGreen) & 0x00ffffff);
        val html = "<font color=\"" + "#000000" + "\">%s</font>"
        val snackbar = Snackbar.make(
            activity.findViewById(android.R.id.content),
            Html.fromHtml(String.format(html, message)),
            Snackbar.LENGTH_SHORT
        )
        val tv = snackbar.view.findViewById<View>(R.id.snackbar_text) as TextView
        ViewCompat.setLayoutDirection(snackbar.view, ViewCompat.LAYOUT_DIRECTION_RTL)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(activity, colorRes))
        tv.setTypeface(XCustomViews.getTypefaces().get(0))
        tv.setTextSize(0, dpToPx(activity.application, 15).toFloat())
        tv.maxLines = 1
        snackbar.show()
        return snackbar
    }

    private fun dpToPx(application: Application, dp: Int): Int {
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            application.resources.displayMetrics
        )
        return px.toInt()
    }

    fun show(activity: Activity, @StringRes res: Int, @ColorRes colorRes: Int): Snackbar? {
        return show(activity, activity.getString(res), colorRes)
    }

}