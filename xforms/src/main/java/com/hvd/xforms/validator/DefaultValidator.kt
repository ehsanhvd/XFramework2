package com.tpa.formbuilder.validator

import android.widget.EditText
import com.hvd.xforms.Validator

class DefaultValidator : Validator {
    override fun isValid(editText: EditText): Boolean {
        return true
    }
}