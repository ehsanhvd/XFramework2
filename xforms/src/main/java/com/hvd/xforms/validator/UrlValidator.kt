package com.tpa.formbuilder.validator

import android.widget.EditText
import com.hvd.xforms.Validator
import com.hvd.xutils.utils.ValidationUtils

class UrlValidator(private val mandatory: Boolean = false) : Validator {
    override fun isValid(editText: EditText): Boolean {
        if (editText.text.toString().isEmpty() && !mandatory){
            return true
        }
        return ValidationUtils.isValidUrl(editText.text.toString())
    }
}