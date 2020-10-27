package com.hvd.xcustomview.fragment

import androidx.fragment.app.Fragment

@Deprecated("use android navigation instead")
abstract class XFragment : Fragment() {
    //implement on frag selected, on back press, app compat activity

    open fun onSelected() {

    }

    public open fun onBackPressed(): Boolean {
        return false
    }
}