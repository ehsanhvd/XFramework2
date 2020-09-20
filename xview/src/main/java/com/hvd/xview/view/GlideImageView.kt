package com.hvd.xcustomview.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.view.View
import android.view.ViewManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hvd.xview.R

open class GlideImageView(context: Context, val attrs: AttributeSet?, val defStyleAttr: Int) :
    ImageView(context, attrs, defStyleAttr) {

    var urlToLoad: String = ""
    var defDrawable: Drawable? = null

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    protected open fun getOptions(): RequestOptions? {
        getPlaceholder()

        val requestOptions = RequestOptions()
        getPlaceholder()?.let { requestOptions.placeholder(it) }
//        requestOptions.placeholder(R.drawable.ic_launcher)
        return requestOptions
    }

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.GlideImageView, defStyleAttr, 0)
        defDrawable = a.getDrawable(R.styleable.GlideImageView_defaultDrawable)
    }

    protected open fun getPlaceholder(): Drawable? {
        return defDrawable
    }

    public fun loadUrl(url: String) {
        this.urlToLoad = url
        val request = Glide.with(getContext())
            .load(url)

        getOptions()?.let {
            request.apply(it)
        }

        request.into(this)
    }

    override fun setImageURI(uri: Uri?) {
        if (uri != null) {
            loadUrl(uri.path!!)
        }
    }

    override fun setImageBitmap(bm: Bitmap?) {
        val request = Glide.with(getContext())
            .load(bm)

        getOptions()?.let {
            request.apply(it)
        }

        request.into(this)
    }

    companion object {

        @JvmStatic
        fun setUrl(imageView: GlideImageView, url: String) {
            imageView.loadUrl(url)
        }

    }

}

fun View.loadImage(id: Int, url: String) {
    findViewById<GlideImageView>(id).loadUrl(url)
}

fun ViewManager.glideImageView() = glideImageView {}
inline fun ViewManager.glideImageView(init: GlideImageView.() -> Unit) =
    ankoView({ GlideImageView(it) }, 0, init)

