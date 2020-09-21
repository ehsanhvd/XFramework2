package com.hvd.xview.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


class XImagePicker {

    var reqCode: Int? = null

    constructor(activity: Activity, listener: (Uri) -> Unit) {
        this@XImagePicker.activity = activity
        this@XImagePicker.listener = listener
    }

    constructor(context: Context, fragment: Fragment, listener: ((Uri) -> Unit)) {
        this@XImagePicker.context = context
        this@XImagePicker.fragment = fragment
        this@XImagePicker.listener = listener
    }

    constructor(activity: Activity) {
        this@XImagePicker.activity = activity
    }

    constructor(context: Context, fragment: Fragment) {
        this@XImagePicker.context = context
        this@XImagePicker.fragment = fragment
    }

    private lateinit var activity: Activity
    private lateinit var fragment: Fragment
    private lateinit var context: Context
    private lateinit var listener: (Uri) -> Unit

    fun startWithActivity(useGallery: Boolean, useCamera: Boolean) {
        reqCode = CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setUseGallery(useGallery)
            .setUseCamera(useCamera)
            .start(activity)
    }

    fun startWithFragment() {
        reqCode = CropImage.activity()
            .start(context, fragment)
    }

    fun startWithActivity(listener: ((Uri) -> Unit)) {
        this@XImagePicker.listener = listener
        reqCode = CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .start(activity)
    }

    fun startWithActivity(listener: ImagePickerListener) {
        startWithActivity {
            listener.onImagePicked(it)
        }
    }

    fun startWithFragment(listener: ((Uri) -> Unit)) {
        this@XImagePicker.listener = listener
        reqCode = CropImage.activity()
            .start(context, fragment)
    }

    fun startWithFragment(listener: ImagePickerListener) {
        startWithFragment {
            listener.onImagePicked(it)
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == reqCode) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result.uri
                listener(resultUri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }

    interface ImagePickerListener {
        fun onImagePicked(uri: Uri)
    }
}