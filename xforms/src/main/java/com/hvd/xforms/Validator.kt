package com.hvd.xforms

import android.widget.EditText

interface Validator {
    fun isValid(editText: EditText): Boolean
}