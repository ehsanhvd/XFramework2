package com.hvd.xcustomview.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hvd.xview.R


class XRecyclerView(context: Context, val attrs: AttributeSet?, val defStyleAttr: Int) :
    RecyclerView(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    var itemWidth = 0

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.XRecyclerView, 0, 0
        )

        itemWidth = typedArray.getDimensionPixelSize(R.styleable.XRecyclerView_itemWidth, -1)

        typedArray.recycle()

        post {
            layoutManager = GridLayoutManager(
                context,
                if (itemWidth == -1 || width == 0) {
                    1
                } else {
                    width / itemWidth
                }
            )
        }
    }
}