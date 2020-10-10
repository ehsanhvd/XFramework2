package com.hvd.xview.viewholder

import android.view.View

interface Renderer<T> {
    fun onRender(view: View, item : T, pos: Int)
}