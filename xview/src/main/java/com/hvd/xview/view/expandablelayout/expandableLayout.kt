package com.hvd.xcustomview.view.expandablelayout

import android.view.ViewManager
import com.hvd.xview.view.expandablelayout.ExpandableLayout
import org.jetbrains.anko.custom.ankoView

fun ViewManager.expandableLayout() = expandableLayout {}
inline fun ViewManager.expandableLayout(init: ExpandableLayout.() -> Unit) =
    ankoView({ ExpandableLayout(it) }, 0, init)
