package com.hvd.xcustomview.fragment

import androidx.fragment.app.Fragment

abstract class XFragment : Fragment() {
    //implement on frag selected, on back press, app compat activity

    open fun onSelected() {

    }

    public open fun onBackPressed(): Boolean {
        return false
    }
}