package com.tpa.formbuilder.validator

import android.widget.EditText
import com.hvd.xforms.Validator

class RangeValidator(val min: Long = -1, val max: Long = -1) : Validator {
    override fun isValid(editText: EditText): Boolean {
        val valString = editText.text.toString()
        val numVal = valString.toLongOrNull();

        if (numVal == null){
            return false
        } else {
            return numVal in (min + 1) until max
        }

    }
}