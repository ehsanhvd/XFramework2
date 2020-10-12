package com.tpa.formbuilder.validator

import android.widget.EditText
import com.hvd.xforms.Validator

class MandatoryValidator : Validator {
    override fun isValid(editText: EditText): Boolean {
        return !editText.text.toString().isEmpty()
    }
}