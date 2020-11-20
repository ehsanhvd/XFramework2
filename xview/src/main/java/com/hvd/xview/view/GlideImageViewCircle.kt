package com.hvd.xview.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewManager
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.custom.ankoView

class GlideImageViewCircle(context: Context, attrs: AttributeSet?, defStyleAttr: Int): GlideImageView(context, attrs, defStyleAttr) {


    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)


    protected override fun getOptions(): RequestOptions? {
        return super.getOptions()?.apply(RequestOptions.circleCropTransform())
    }

}

fun ViewManager.glideImageViewCircle() = glideImageViewCircle {}
inline fun ViewManager.glideImageViewCircle(init: GlideImageViewCircle.() -> Unit) =
    ankoView({ GlideImageViewCircle(it) },0,  init)
